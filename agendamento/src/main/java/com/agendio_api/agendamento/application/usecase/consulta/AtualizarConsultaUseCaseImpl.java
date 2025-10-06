package com.agendio_api.agendamento.application.usecase.consulta;

import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import com.agendio_api.agendamento.application.port.dto.consulta.AtualizaConsultaDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.AtualizarConsultaUseCase;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.exception.ConsultaNaoEncontradaException;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;
import com.agendio_api.agendamento.domain.validator.ConsultaValidator;
import com.agendio_api.agendamento.infrastructure.adapter.messaging.rabbitmq.ConsultasPublisher;

public class AtualizarConsultaUseCaseImpl implements AtualizarConsultaUseCase {
    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;
    private final List<ConsultaValidator> consultaValidators;
    private final ConsultasPublisher consultasPublisher;

    public AtualizarConsultaUseCaseImpl(ConsultaGateway consultaGateway,
            IConsultaMapper consultaMapper,
            List<ConsultaValidator> consultaValidators,
            ConsultasPublisher consultasPublisher) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
        this.consultaValidators = consultaValidators;
        this.consultasPublisher = consultasPublisher;
    }

    @Override
    public ConsultaResponseDTO executar(UUID id, AtualizaConsultaDTO atualizaConsultaDTO) {
        Consulta consultaExistente = consultaGateway.buscarPorId(id)
                .orElseThrow(() -> new ConsultaNaoEncontradaException("Consulta com ID " + id + " nao encontrada."));

        // aplica alterações no domínio a partir do DTO
        Consulta consultaAtualizada = consultaMapper.toDomain(atualizaConsultaDTO, consultaExistente);

        // validações
        consultaValidators.forEach(validator -> validator.validar(consultaAtualizada));

        // persiste
        Consulta consultaSalva = consultaGateway.atualizar(consultaAtualizada);

        // LocalDateTime -> ISO-8601 com timezone de Brasília (America/Sao_Paulo)
        String dataHoraISO = consultaSalva.getHorarioSolicitado()
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .toOffsetDateTime()
                .toString();

        // publica evento de edição (sem pacienteContato)
        consultasPublisher.publicarConsultaEditada(
                consultaSalva.getId().toString(),
                null,
                dataHoraISO);

        return consultaMapper.toResponseDTO(consultaSalva);
    }
}
