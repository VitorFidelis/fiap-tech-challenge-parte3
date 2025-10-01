package gateway.br.com.gateway.application.dto.paciente;


import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;

public record PacienteFiltroPaginadoRequestDTO(
        PaginatedRequestDTO paginatedRequest,
        PacienteInputDTO filtro
) {
}
