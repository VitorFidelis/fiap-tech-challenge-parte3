package com.agendio_api.agendamento.domain.model.consulta;

import com.agendio_api.agendamento.domain.model.valueobject.PeriodoConsulta;

import java.util.UUID;

public record FiltroConsulta(UUID medicoId, PeriodoConsulta periodo) {
    public FiltroConsulta {
        if (medicoId == null) {
            throw new IllegalArgumentException("O id do médico é obrigatório.");
        }
        if (periodo == null) {
            throw new IllegalArgumentException("O período é obrigatório.");
        }
    }
}
