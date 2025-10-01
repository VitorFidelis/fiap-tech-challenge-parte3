package gateway.br.com.gateway.application.dto.paginated;

import java.util.List;
import java.util.Objects;

public record PaginatedResult<T>(
        List<T> content,
        long totalItems,
        int totalPages
) {
    public PaginatedResult {
        Objects.requireNonNull(content, "Conteúdo não pode ser nulo");

        if (totalItems < 0) {
            throw new IllegalArgumentException("Quantidade de itens nao pode ser negativa");
        }
        if (totalPages < 0) {
            throw new IllegalArgumentException("Quantidade de paginas nao pode ser negativa");
        }
    }
}
