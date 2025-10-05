package com.agendio_api.agendamento.domain.exception;


public class EmailJaExisteException extends RuntimeException {

    public EmailJaExisteException(String message) {
        super(message);
    }
}
