package com.agendio_api.agendamento.application.port.dto.consulta;

import com.agendio_api.agendamento.domain.model.consulta.StatusConsulta;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para representar os dados de resposta de uma consulta.
 * Segue o princípio da responsabilidade única (SOLID) ao representar apenas os dados necessários para a camada de apresentação.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ConsultaResponseDTO(
        UUID id,
        UUID medicoId,
        UUID pacienteId,
        UUID enfermeiroId,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime horarioSolicitado,

        StatusConsulta status,
        String observacoes,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime criadoEm,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime atualizadoEm
) {
}
