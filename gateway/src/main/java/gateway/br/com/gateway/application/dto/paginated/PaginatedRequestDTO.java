package gateway.br.com.gateway.application.dto.paginated;

import java.util.Objects;

public record PaginatedRequestDTO(int page,
                                  int size,
                                  String sort
) {
    public PaginatedRequestDTO {
        if (page < 0) {
            throw new IllegalArgumentException("Página não pode ser negativa");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Tamanho da página deve ser maior que zero");
        }
        Objects.requireNonNull(sort, "Critério de ordenação não pode ser nulo");
    }
}