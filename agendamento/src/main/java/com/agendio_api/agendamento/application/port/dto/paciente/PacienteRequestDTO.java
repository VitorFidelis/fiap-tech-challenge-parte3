package com.agendio_api.agendamento.application.port.dto.paciente;

import com.agendio_api.agendamento.application.port.dto.usuario.UsuarioRequest;

import java.util.UUID;

public record PacienteRequestDTO(
        UUID id,
        String nome,
        String email
) implements UsuarioRequest {
}

