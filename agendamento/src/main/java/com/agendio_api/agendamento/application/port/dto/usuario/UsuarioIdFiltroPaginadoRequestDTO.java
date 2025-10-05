package com.agendio_api.agendamento.application.port.dto.usuario;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;

import java.util.UUID;

public record UsuarioIdFiltroPaginadoRequestDTO(
        UUID usuarioId,
        PaginatedRequestDTO paginacao

) {
}
