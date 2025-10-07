package com.agendio_api.agendamento.application.usecase.consulta;

import java.time.ZoneId;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.agendio_api.agendamento.application.port.dto.consulta.AgendaConsultaDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.AgendarConsultaUseCase;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;
import com.agendio_api.agendamento.domain.validator.ConsultaValidator;
import com.agendio_api.agendamento.infrastructure.adapter.messaging.rabbitmq.ConsultasPublisher;

public class AgendarConsultaUseCaseImpl implements AgendarConsultaUseCase {

    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;
    private final List<ConsultaValidator> consultaValidators;
    private final ConsultasPublisher consultasPublisher;

    public AgendarConsultaUseCaseImpl(ConsultaGateway consultaGateway,
            IConsultaMapper consultaMapper,
            List<ConsultaValidator> consultaValidators,
            ConsultasPublisher consultasPublisher) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
        this.consultaValidators = consultaValidators;
        this.consultasPublisher = consultasPublisher;
    }

    @Transactional
    @Override
    public ConsultaResponseDTO executar(AgendaConsultaDTO agendaConsultaDTO) {
        Consulta consulta = consultaMapper.toDomain(agendaConsultaDTO);
        validaConsulta(consulta);

        Consulta consultaSalva = consultaGateway.agendar(consulta);

        // Converte LocalDateTime -> ISO-8601 com timezone America/Recife
        String dataHoraISO = consultaSalva.getHorarioSolicitado()
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .toOffsetDateTime()
                .toString();

        // Publica evento para o serviço de Notificações (sem pacienteContato)
        consultasPublisher.publicarConsultaCriada(
                consultaSalva.getId().toString(),
                null, // não temos contato do paciente nos DTOs/entidade
                dataHoraISO);

        return consultaMapper.toResponseDTO(consultaSalva);
    }

    private void validaConsulta(Consulta consulta) {
        for (ConsultaValidator validator : consultaValidators) {
            validator.validar(consulta);
        }
    }
}
