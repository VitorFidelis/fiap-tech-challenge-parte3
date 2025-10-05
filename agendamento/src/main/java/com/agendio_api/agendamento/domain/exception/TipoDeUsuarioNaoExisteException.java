package com.agendio_api.agendamento.domain.exception;


public class TipoDeUsuarioNaoExisteException extends RuntimeException {

    public TipoDeUsuarioNaoExisteException(String message) {
        super(message);
    }
}
