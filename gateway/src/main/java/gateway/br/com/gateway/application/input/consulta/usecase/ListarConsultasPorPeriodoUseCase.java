package gateway.br.com.gateway.application.input.consulta.usecase;

import gateway.br.com.gateway.application.dto.consulta.ConsultaFiltroRequestDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;

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
