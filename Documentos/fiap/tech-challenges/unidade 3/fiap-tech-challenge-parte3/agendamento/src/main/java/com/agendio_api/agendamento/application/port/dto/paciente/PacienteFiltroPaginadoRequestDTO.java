package com.agendio_api.agendamento.application.port.dto.paciente;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;

public record PacienteFiltroPaginadoRequestDTO(
        PaginatedRequestDTO paginatedRequest,
        PacienteRequestDTO filtro
) {
}
