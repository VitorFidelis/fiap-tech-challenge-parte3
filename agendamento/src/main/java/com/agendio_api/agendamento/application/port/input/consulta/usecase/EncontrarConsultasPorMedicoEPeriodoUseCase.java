package com.agendio_api.agendamento.application.port.input.consulta.usecase;

import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaFiltroRequestDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;

public interface EncontrarConsultasPorMedicoEPeriodoUseCase {

    /**
     * Lista as consultas de um médico em um período específico, com paginação.
     *
     * @param filtro    Dados para filtrar consultas (ex.: ID do médico, intervalo de datas)
     * @param paginacao Configuração de paginação (ex.: página, tamanho, ordenação)
     * @return Uma lista paginada de consultas do médico no período solicitado
     */
    PaginatedResponseDTO<ConsultaResponseDTO> executar(
            ConsultaFiltroRequestDTO filtro,
            PaginatedRequestDTO paginacao
    );
}
