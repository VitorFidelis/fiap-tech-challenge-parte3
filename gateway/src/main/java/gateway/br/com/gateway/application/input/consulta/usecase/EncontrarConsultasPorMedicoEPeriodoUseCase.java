package gateway.br.com.gateway.application.input.consulta.usecase;

import gateway.br.com.gateway.application.dto.consulta.ConsultaFiltroRequestDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;

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
