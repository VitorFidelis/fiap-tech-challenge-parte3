package com.agendio_api.agendamento.application.port.dto.consulta.graphql;

import com.agendio_api.agendamento.domain.model.consulta.StatusConsulta;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.time.LocalDateTime;
import java.util.UUID;

@SchemaMapping("AtualizarConsultaInput")
public record AtualizarConsultaGraphqlDTO(
        UUID id,
        StatusConsulta status,
        LocalDateTime horarioSolicitado,
        String observacoes
) {
    public void validar() {
        if (id == null) {
            throw new IllegalArgumentException("O ID da consulta é obrigatório");
        }

        if (horarioSolicitado != null && horarioSolicitado.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data da consulta deve ser futura");
        }

        if (observacoes != null && observacoes.length() > 500) {
            throw new IllegalArgumentException("As observações não podem ultrapassar 500 caracteres");
        }

        if (horarioSolicitado == null && status == null && observacoes == null) {
            throw new IllegalArgumentException("Pelo menos um campo deve ser fornecido para a atualização (horário, status ou observações).");
        }
    }
}
