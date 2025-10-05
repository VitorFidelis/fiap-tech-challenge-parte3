package com.agendio_api.agendamento.infrastructure.exception;

public class GatewayException extends RuntimeException {
    public GatewayException(String s) {
        super(s);
    }
}
