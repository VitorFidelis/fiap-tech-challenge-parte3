package com.agendio_api.agendamento.application.usecase.medico;

import com.agendio_api.agendamento.application.port.input.medico.usecase.ReativarMedicoUseCase;
import com.agendio_api.agendamento.application.port.output.usuario.medico.MedicoGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class ReativarMedicoUseCaseImpl implements ReativarMedicoUseCase {
    private final MedicoGateway medicoGateway;

    public ReativarMedicoUseCaseImpl(MedicoGateway medicoGateway) {
        this.medicoGateway = medicoGateway;
    }

    @Transactional
    @Override
    public void executar(UUID id) {
        medicoGateway.reativar(id);
    }
}
