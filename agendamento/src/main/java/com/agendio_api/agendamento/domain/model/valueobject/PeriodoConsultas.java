package com.agendio_api.agendamento.domain.model.valueobject;

import java.time.LocalDate;

public record PeriodoConsultas(LocalDate inicio, LocalDate fim) {
    public PeriodoConsultas {
        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("O início e fim do período não podem ser nulos");
        }
        if (!inicio.isBefore(fim)) {
            throw new IllegalArgumentException("O início do período deve ser antes do fim");
        }
    }

    public boolean contem(LocalDate data) {
        return !data.isBefore(inicio) && !data.isAfter(fim);
    }

    public boolean sobrepoe(PeriodoConsultas outroPeriodo) {
        return inicio.isBefore(outroPeriodo.fim) && fim.isAfter(outroPeriodo.inicio);
    }
}

