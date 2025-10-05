package com.agendio_api.agendamento.application.port.input.consulta.usecase;

import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.dto.usuario.UsuarioIdFiltroPaginadoRequestDTO;

public interface EncontrarConsultasPorMedicoUseCase {
    PaginatedResponseDTO<ConsultaResponseDTO> executar(UsuarioIdFiltroPaginadoRequestDTO dto);

}
