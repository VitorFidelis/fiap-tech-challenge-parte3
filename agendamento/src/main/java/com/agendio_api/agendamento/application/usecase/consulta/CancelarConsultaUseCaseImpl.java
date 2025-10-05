package com.agendio_api.agendamento.application.usecase.consulta;

import com.agendio_api.agendamento.application.port.input.consulta.usecase.CancelarConsultaUseCase;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.exception.ConsultaNaoEncontradaException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class CancelarConsultaUseCaseImpl implements CancelarConsultaUseCase {

    private final ConsultaGateway consultaGateway;

    public CancelarConsultaUseCaseImpl(ConsultaGateway consultaGateway) {
        this.consultaGateway = consultaGateway;
    }

    @Transactional
    @Override
    public void executar(UUID id, String motivoCancelamento) {
        if (consultaGateway.buscarPorId(id).isEmpty())
            throw new ConsultaNaoEncontradaException("Consulta com ID " + id + " nao encontrada.");
        consultaGateway.cancelar(id, motivoCancelamento);
    }
}
