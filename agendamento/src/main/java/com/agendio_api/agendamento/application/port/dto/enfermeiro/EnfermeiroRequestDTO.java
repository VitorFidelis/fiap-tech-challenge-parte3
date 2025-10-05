package com.agendio_api.agendamento.application.port.dto.enfermeiro;

import com.agendio_api.agendamento.application.port.dto.usuario.UsuarioRequest;

public record EnfermeiroRequestDTO(
        String nome,
        String email,
        String coren
) implements UsuarioRequest {
}

