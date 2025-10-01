package gateway.br.com.gateway.application.dto.enfermeiro;


import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;

import java.util.UUID;

public record EnfermeiroFiltroPaginadoRequestDTO(
        UUID id,
        PaginatedRequestDTO paginatedRequest,
        EnfermeiroInputDTO filtro
) {
}
