package com.agendio_api.agendamento.application.port.input.paciente.usecase;

import com.agendio_api.agendamento.application.port.dto.paciente.PacienteResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;

public interface EncontrarTodosPacientesUseCase {

    PaginatedResponseDTO<PacienteResponseDTO> executar(PaginatedRequestDTO paginacao);
}
