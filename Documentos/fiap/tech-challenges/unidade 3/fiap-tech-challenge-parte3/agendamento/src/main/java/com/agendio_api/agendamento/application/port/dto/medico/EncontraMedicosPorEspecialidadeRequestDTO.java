package com.agendio_api.agendamento.application.port.dto.medico;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EncontraMedicosPorEspecialidadeRequestDTO(
        @NotBlank(message = "A especialidade é obrigatória")
        @Size(max = 100, message = "A especialidade não pode ter mais de 100 caracteres")
        String especialidade,

        @Valid
        @NotNull(message = "Os parâmetros de paginação são obrigatórios")
        PaginatedRequestDTO paginacao) {

}
