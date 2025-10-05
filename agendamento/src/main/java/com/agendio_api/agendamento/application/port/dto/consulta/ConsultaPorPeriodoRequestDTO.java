package com.agendio_api.agendamento.application.port.dto.consulta;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ConsultaPorPeriodoRequestDTO(
        @NotNull(message = "a data de início deve ser informada")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate inicio,

        @NotNull(message = "a data de fim deve ser informada")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate fim
) {
    public ConsultaPorPeriodoRequestDTO {
        if (inicio != null && fim != null && inicio.isAfter(fim)) {
            throw new IllegalArgumentException("Data início não pode ser posterior à data fim");
        }
    }
}

