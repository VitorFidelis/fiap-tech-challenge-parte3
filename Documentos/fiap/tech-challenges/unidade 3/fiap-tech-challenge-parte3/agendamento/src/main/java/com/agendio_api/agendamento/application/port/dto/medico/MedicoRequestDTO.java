package com.agendio_api.agendamento.application.port.dto.medico;


import com.agendio_api.agendamento.application.port.dto.usuario.UsuarioRequest;

public record MedicoRequestDTO(
        String nome,
        String email,
        String crm
) implements UsuarioRequest {
}

