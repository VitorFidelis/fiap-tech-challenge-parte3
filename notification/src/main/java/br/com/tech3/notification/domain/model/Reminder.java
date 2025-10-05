package br.com.tech3.notification.domain.model;

import java.time.OffsetDateTime;

public record Reminder(
        String consultaId,
        String whenLabel,
        OffsetDateTime scheduledAt,
        String status,
        String note
) { }