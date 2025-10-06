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
){
    public void validar() {
        if (medicoId == null) {
            throw new IllegalArgumentException("O ID do médico é obrigatório");
        }

        if (pacienteId == null) {
            throw new IllegalArgumentException("O ID do paciente é obrigatório");
        }

        if (enfermeiroId == null) {
            throw new IllegalArgumentException("O ID do enfermeiro é obrigatório");
        }

        if (horarioSolicitado != null && horarioSolicitado.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data da consulta deve ser futura");
        }

        if (observacoes != null && observacoes.length() > 500) {
            throw new IllegalArgumentException("As observações não podem ultrapassar 500 caracteres");
        }
    }
}

