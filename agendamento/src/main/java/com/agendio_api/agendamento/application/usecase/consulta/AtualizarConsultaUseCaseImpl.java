package com.agendio_api.agendamento.application.usecase.consulta;

import com.agendio_api.agendamento.application.port.dto.consulta.AtualizaConsultaDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.AtualizarConsultaUseCase;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.exception.ConsultaNaoEncontradaException;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;
import com.agendio_api.agendamento.domain.validator.ConsultaValidator;

import java.util.List;
import java.util.UUID;

public class AtualizarConsultaUseCaseImpl implements AtualizarConsultaUseCase {
    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;
    private final java.util.List<ConsultaValidator> consultaValidators;

    public AtualizarConsultaUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper, List<ConsultaValidator> consultaValidators) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
        this.consultaValidators = consultaValidators;
    }

    @Override
    public ConsultaResponseDTO executar(UUID id, AtualizaConsultaDTO atualizaConsultaDTO) {
        Consulta consultaExistente = consultaGateway.buscarPorId(id)
                .orElseThrow(() -> new ConsultaNaoEncontradaException("Consulta com ID " + id + " nao encontrada."));
        Consulta consultaParaValidar = consultaMapper.toDomain(atualizaConsultaDTO, consultaExistente);
        consultaValidators.forEach(validator -> validator.validar(consultaParaValidar));
        Consulta consultaParaSalvar = consultaGateway.atualizar(consultaParaValidar);
        return consultaMapper.toResponseDTO(consultaParaSalvar);
    }
}
