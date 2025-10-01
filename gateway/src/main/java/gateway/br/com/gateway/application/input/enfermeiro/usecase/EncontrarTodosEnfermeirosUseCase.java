package gateway.br.com.gateway.application.input.enfermeiro.usecase;

import gateway.br.com.gateway.application.dto.enfermeiro.EnfermeiroResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;

public interface EncontrarTodosEnfermeirosUseCase {
    PaginatedResponseDTO<EnfermeiroResponseDTO> executar(
            PaginatedRequestDTO paginacao);
}
