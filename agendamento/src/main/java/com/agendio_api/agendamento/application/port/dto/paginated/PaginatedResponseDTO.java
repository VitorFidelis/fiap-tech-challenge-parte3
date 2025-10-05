package com.agendio_api.agendamento.application.port.dto.paginated;

import java.util.List;

public record PaginatedResponseDTO<T>(List<T> content,
                                      long totalItems,
                                      int totalPages,
                                      int currentPage,
                                      int pageSize) {
}
