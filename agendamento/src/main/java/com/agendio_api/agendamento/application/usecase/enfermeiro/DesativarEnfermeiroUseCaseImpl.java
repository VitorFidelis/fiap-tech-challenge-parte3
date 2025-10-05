package com.agendio_api.agendamento.application.usecase.enfermeiro;

import com.agendio_api.agendamento.application.port.input.enfermeiro.usecase.DesativarEnfermeiroUseCase;
import com.agendio_api.agendamento.application.port.output.usuario.enfermeiro.EnfermeiroGateway;
import com.agendio_api.agendamento.domain.exception.UsuarioNaoEncontradoException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class DesativarEnfermeiroUseCaseImpl implements DesativarEnfermeiroUseCase {
    private final EnfermeiroGateway enfermeiroGateway;

    public DesativarEnfermeiroUseCaseImpl(EnfermeiroGateway enfermeiroGateway) {
        this.enfermeiroGateway = enfermeiroGateway;
    }

    @Transactional
    @Override
    public void execute(UUID id) {
        if (enfermeiroGateway.buscarPorId(id).isEmpty())
            throw new UsuarioNaoEncontradoException("Enfermeiro não encontrado.");
        enfermeiroGateway.desativar(id);
    }
}
