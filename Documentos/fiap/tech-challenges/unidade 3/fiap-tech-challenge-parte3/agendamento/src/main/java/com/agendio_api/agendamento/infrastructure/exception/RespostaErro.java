package com.agendio_api.agendamento.infrastructure.exception;

import java.time.LocalDateTime;

public record RespostaErro(int status, String mensagem, LocalDateTime timestamp) {
}
