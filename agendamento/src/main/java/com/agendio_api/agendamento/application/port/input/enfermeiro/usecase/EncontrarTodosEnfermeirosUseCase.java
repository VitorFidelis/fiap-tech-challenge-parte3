package com.agendio_api.agendamento.application.port.input.enfermeiro.usecase;

import com.agendio_api.agendamento.application.port.dto.enfermeiro.EnfermeiroResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;

public interface EncontrarTodosEnfermeirosUseCase {
    PaginatedResponseDTO<EnfermeiroResponseDTO> executar(
            PaginatedRequestDTO paginacao);
}
