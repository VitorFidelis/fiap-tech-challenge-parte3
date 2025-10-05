package com.agendio_api.agendamento.application.port.dto.usuario;

public record UsuarioRequestDTO(
        String nome,
        String email
) implements UsuarioRequest {
}
