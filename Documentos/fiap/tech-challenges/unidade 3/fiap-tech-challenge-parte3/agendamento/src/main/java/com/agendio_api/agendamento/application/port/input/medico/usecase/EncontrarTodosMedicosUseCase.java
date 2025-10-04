package com.agendio_api.agendamento.application.port.input.medico.usecase;

import com.agendio_api.agendamento.application.port.dto.medico.MedicoResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;

public interface EncontrarTodosMedicosUseCase {
    PaginatedResponseDTO<MedicoResponseDTO> executar(PaginatedRequestDTO paginatedRequest);
}
