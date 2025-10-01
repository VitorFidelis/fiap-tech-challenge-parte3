package gateway.br.com.gateway.application.dto.paginated;

import java.util.List;

public record PaginatedResponseDTO<T>(List<T> content,
                                      long totalItems,
                                      int totalPages,
                                      int currentPage,
                                      int pageSize) {
}
