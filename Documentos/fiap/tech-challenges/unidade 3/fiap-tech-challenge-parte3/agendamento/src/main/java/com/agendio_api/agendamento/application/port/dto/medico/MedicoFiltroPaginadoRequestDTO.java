package com.agendio_api.agendamento.application.port.dto.medico;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;

import java.util.UUID;

public record MedicoFiltroPaginadoRequestDTO(
        UUID medicoId,
        PaginatedRequestDTO paginatedRequest,
        MedicoRequestDTO filtro
) {
}
