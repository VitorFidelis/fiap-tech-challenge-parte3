package br.com.tech3.notification.domain.model;

import java.time.OffsetDateTime;
import java.util.Map;

public record ConsultationEvent(
        String tipo,
        String consultaId,
        OffsetDateTime dataHoraConsulta,
        String pacienteContato,
        Map<String, Object> extras
) { }