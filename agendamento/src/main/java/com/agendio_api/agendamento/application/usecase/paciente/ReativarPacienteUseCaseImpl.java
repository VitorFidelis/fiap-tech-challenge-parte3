package com.agendio_api.agendamento.application.usecase.paciente;

import com.agendio_api.agendamento.application.port.input.paciente.usecase.ReativarPacienteUseCase;
import com.agendio_api.agendamento.application.port.output.usuario.paciente.PacienteGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class ReativarPacienteUseCaseImpl implements ReativarPacienteUseCase {
    private final PacienteGateway pacienteGateway;

    public ReativarPacienteUseCaseImpl(PacienteGateway pacienteGateway) {
        this.pacienteGateway = pacienteGateway;
    }

    @Transactional
    @Override
    public void executar(UUID id) {
        pacienteGateway.reativar(id);
    }
}
