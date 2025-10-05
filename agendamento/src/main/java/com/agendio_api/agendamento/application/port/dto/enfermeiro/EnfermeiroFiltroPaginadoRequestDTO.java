package com.agendio_api.agendamento.application.port.dto.enfermeiro;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;

import java.util.UUID;

public record EnfermeiroFiltroPaginadoRequestDTO(
        UUID id,
        PaginatedRequestDTO paginatedRequest,
        EnfermeiroRequestDTO filtro
) {
}
