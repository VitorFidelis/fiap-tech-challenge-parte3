package com.agendio_api.agendamento.application.usecase.enfermeiro;

import com.agendio_api.agendamento.application.port.input.enfermeiro.usecase.ReativarEnfermeiroUseCase;
import com.agendio_api.agendamento.application.port.output.usuario.enfermeiro.EnfermeiroGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class ReativarEnfermeiroUseCaseImpl implements ReativarEnfermeiroUseCase {
    private final EnfermeiroGateway enfermeiroGateway;

    public ReativarEnfermeiroUseCaseImpl(EnfermeiroGateway enfermeiroGateway) {
        this.enfermeiroGateway = enfermeiroGateway;
    }

    @Transactional
    @Override
    public void executar(UUID id) {
        enfermeiroGateway.reativar(id);
    }
}
