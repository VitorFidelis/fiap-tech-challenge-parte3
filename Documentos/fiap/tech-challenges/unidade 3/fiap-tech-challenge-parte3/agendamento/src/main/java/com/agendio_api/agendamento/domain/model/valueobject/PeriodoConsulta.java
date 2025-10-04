package com.agendio_api.agendamento.domain.model.valueobject;

import java.time.LocalDateTime;

public record PeriodoConsulta(LocalDateTime inicio, LocalDateTime fim) {
    public PeriodoConsulta {
        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("O início e fim do período não podem ser nulos");
        }
        if (!inicio.isBefore(fim)) {
            throw new IllegalArgumentException("O início do período deve ser antes do fim");
        }
    }

    public boolean contem(LocalDateTime dataHora) {
        return !dataHora.isBefore(inicio) && !dataHora.isAfter(fim);
    }

    public boolean sobrepoe(PeriodoConsulta outroPeriodo) {
        return inicio.isBefore(outroPeriodo.fim) && fim.isAfter(outroPeriodo.inicio);
    }
}

