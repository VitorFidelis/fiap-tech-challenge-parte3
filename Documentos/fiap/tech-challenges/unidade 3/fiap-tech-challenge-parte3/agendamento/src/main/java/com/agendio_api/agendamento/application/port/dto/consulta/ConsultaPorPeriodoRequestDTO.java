package com.agendio_api.agendamento.application.port.dto.consulta;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaPorPeriodoRequestDTO(
        UUID medicoId,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
        int page,
        int size,
        String sort
) {
}

