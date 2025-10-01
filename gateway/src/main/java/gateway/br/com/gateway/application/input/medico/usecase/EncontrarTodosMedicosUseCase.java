package gateway.br.com.gateway.application.input.medico.usecase;

import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;

public interface EncontrarTodosMedicosUseCase {
    PaginatedResponseDTO<MedicoResponseDTO> executar(PaginatedRequestDTO paginatedRequest);
}
