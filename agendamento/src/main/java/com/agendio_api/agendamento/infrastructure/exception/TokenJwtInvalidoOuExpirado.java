package com.agendio_api.agendamento.infrastructure.exception;

public class TokenJwtInvalidoOuExpirado extends RuntimeException {
    public TokenJwtInvalidoOuExpirado(String message) {
        super(message);
    }
}
