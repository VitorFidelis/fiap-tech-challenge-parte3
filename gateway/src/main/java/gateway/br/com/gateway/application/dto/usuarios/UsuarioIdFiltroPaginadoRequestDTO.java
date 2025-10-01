package gateway.br.com.gateway.application.dto.usuarios;

import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;

import java.util.UUID;

public record UsuarioIdFiltroPaginadoRequestDTO(
        UUID usuarioId,
        PaginatedRequestDTO paginacao

) {
}
