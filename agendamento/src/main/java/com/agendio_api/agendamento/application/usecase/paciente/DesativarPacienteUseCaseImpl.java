package com.agendio_api.agendamento.application.usecase.paciente;

import com.agendio_api.agendamento.application.port.input.paciente.usecase.DesativarPacienteUseCase;
import com.agendio_api.agendamento.application.port.output.usuario.paciente.PacienteGateway;
import com.agendio_api.agendamento.domain.exception.UsuarioNaoEncontradoException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class DesativarPacienteUseCaseImpl implements DesativarPacienteUseCase {
    private final PacienteGateway pacienteGateway;

    public DesativarPacienteUseCaseImpl(PacienteGateway pacienteGateway) {
        this.pacienteGateway = pacienteGateway;
    }

    @Transactional
    @Override
    public void executar(UUID id) {
        if (pacienteGateway.buscarPorId(id).isEmpty())
            throw new UsuarioNaoEncontradoException("Paciente não encontrado.");
        pacienteGateway.desativar(id);
    }
}
