package com.agendio_api.agendamento.application.port.dto.consulta.graphql;

import com.agendio_api.agendamento.domain.model.consulta.StatusConsulta;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.time.LocalDateTime;
import java.util.UUID;

@SchemaMapping("Consulta")
public record ConsultaResponseGraphqlDTO(
        UUID id,
        UUID medicoId,
        UUID pacienteId,
        UUID enfermeiroId,
        LocalDateTime horarioSolicitado,
        StatusConsulta status,
        String observacoes,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
