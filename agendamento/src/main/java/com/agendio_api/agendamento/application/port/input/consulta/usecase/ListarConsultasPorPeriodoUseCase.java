package com.agendio_api.agendamento.application.port.input.consulta.usecase;

import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaFiltroRequestDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;

public interface ListarConsultasPorPeriodoUseCase {
    /**
     * Lista consultas dentro de um período, com filtros adicionais e paginação.
     *
     * @param filtro    Filtros para a busca (datas, médico, paciente, etc.)
     * @param paginacao Parâmetros de paginação (página, tamanho, ordenação)
     * @return Lista paginada de consultas
     */
    PaginatedResponseDTO<ConsultaResponseDTO> executar(
            ConsultaFiltroRequestDTO filtro,
            PaginatedRequestDTO paginacao
    );
}
