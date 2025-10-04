package com.agendio_api.agendamento.application.usecase.medico;

import com.agendio_api.agendamento.application.port.input.medico.usecase.DesativarMedicoUseCase;
import com.agendio_api.agendamento.application.port.output.usuario.medico.MedicoGateway;
import com.agendio_api.agendamento.domain.exception.UsuarioNaoEncontradoException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class DesativarMedicoUseCaseImpl implements DesativarMedicoUseCase {
    private final MedicoGateway medicoGateway;

    public DesativarMedicoUseCaseImpl(MedicoGateway medicoGateway) {
        this.medicoGateway = medicoGateway;
    }

    @Transactional
    @Override
    public void executar(UUID id) {
        if (medicoGateway.buscarPorId(id).isEmpty()) throw new UsuarioNaoEncontradoException("Medico não encontrado.");
        medicoGateway.desativar(id);
    }
}
