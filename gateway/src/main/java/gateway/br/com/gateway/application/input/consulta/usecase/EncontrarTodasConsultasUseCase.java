package gateway.br.com.gateway.application.input.consulta.usecase;


import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;

public interface EncontrarTodasConsultasUseCase {
    PaginatedResponseDTO<ConsultaResponseDTO> executar(PaginatedRequestDTO paginacao);
}
