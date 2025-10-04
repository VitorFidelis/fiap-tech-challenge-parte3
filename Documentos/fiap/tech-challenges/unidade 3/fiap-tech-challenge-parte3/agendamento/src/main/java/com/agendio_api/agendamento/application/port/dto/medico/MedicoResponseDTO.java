package com.agendio_api.agendamento.application.port.dto.medico;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MedicoResponseDTO(
        UUID id,
        String nome,
        String email,
        String crm,
        String especialidade,
        boolean ativo,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime criadoEm,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime atualizadoEm
) {
}
