package com.agendio_api.agendamento.application.usecase.consulta;

import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.EncontrarConsultaPorIdUseCase;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.exception.ConsultaNaoEncontradaException;

import java.util.UUID;

public class EncontrarConsultaPorIdUseCaseImpl implements EncontrarConsultaPorIdUseCase {
    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;

    public EncontrarConsultaPorIdUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
    }

    @Override
    public ConsultaResponseDTO executar(UUID id) {
        return consultaMapper
                .toResponseDTO(consultaGateway.buscarPorId(id)
                        .orElseThrow(() -> new ConsultaNaoEncontradaException("Consulta com ID " + id + " nao encontrada.")));
    }
}
