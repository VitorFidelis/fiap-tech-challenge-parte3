package com.agendio_api.agendamento.application.usecase.enfermeiro;

import com.agendio_api.agendamento.application.port.dto.enfermeiro.EnfermeiroResponseDTO;
import com.agendio_api.agendamento.application.port.input.enfermeiro.usecase.EncontrarEnfermeiroUseCase;
import com.agendio_api.agendamento.application.port.mapper.enfermeiro.IEnfermeiroMapper;
import com.agendio_api.agendamento.application.port.output.usuario.enfermeiro.EnfermeiroGateway;
import com.agendio_api.agendamento.domain.exception.UsuarioNaoEncontradoException;

import java.util.UUID;

public class EncontrarEnfermeiroUseCaseImpl implements EncontrarEnfermeiroUseCase {
    private final EnfermeiroGateway enfermeiroGateway;
    private final IEnfermeiroMapper enfermeiroMapper;

    public EncontrarEnfermeiroUseCaseImpl(EnfermeiroGateway enfermeiroGateway, IEnfermeiroMapper enfermeiroMapper) {
        this.enfermeiroGateway = enfermeiroGateway;
        this.enfermeiroMapper = enfermeiroMapper;
    }

    @Override
    public EnfermeiroResponseDTO execute(UUID id) {
        return enfermeiroMapper.toResponseDTO(
                enfermeiroGateway
                        .buscarPorId(id)
                        .orElseThrow(
                                () -> new UsuarioNaoEncontradoException("Enfermeiro não encontrado.")));
    }
}
