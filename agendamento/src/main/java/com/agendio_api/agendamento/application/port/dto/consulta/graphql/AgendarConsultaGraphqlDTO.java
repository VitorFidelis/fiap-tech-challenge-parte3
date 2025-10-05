package com.agendio_api.agendamento.application.port.dto.consulta.graphql;

import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.time.LocalDateTime;
import java.util.UUID;

@SchemaMapping("AgendarConsultaInput")
public record AgendarConsultaGraphqlDTO(
        UUID medicoId,
        UUID pacienteId,
        UUID enfermeiroId,
        LocalDateTime horarioSolicitado,
        String observacoes
) {
}
