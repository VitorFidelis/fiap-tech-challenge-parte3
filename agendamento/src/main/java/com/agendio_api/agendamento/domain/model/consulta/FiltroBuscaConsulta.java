package com.agendio_api.agendamento.domain.model.consulta;

import com.agendio_api.agendamento.domain.model.valueobject.PeriodoConsultas;

import java.util.UUID;

public record FiltroBuscaConsulta(UUID medicoId, PeriodoConsultas periodo) {
    public FiltroBuscaConsulta {
        if (medicoId == null) {
            throw new IllegalArgumentException("O id do médico é obrigatório.");
        }
        if (periodo == null) {
            throw new IllegalArgumentException("O período é obrigatório.");
        }
    }
}
