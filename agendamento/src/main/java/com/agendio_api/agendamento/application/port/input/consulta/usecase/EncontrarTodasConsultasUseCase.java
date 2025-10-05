package com.agendio_api.agendamento.application.port.input.consulta.usecase;

import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;

public interface EncontrarTodasConsultasUseCase {
    PaginatedResponseDTO<ConsultaResponseDTO> executar(PaginatedRequestDTO paginacao);
}
