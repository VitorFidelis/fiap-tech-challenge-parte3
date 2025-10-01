package gateway.br.com.gateway.application.dto.medico;

import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;

import java.util.UUID;

public record MedicoFiltroPaginadoRequestDTO(
        UUID medicoId,
        PaginatedRequestDTO paginatedRequest,
        MedicoInputDTO filtro
) {
}
