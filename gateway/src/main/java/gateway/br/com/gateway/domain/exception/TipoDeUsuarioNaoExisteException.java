package gateway.br.com.gateway.domain.exception;


public class TipoDeUsuarioNaoExisteException extends RuntimeException {

    public TipoDeUsuarioNaoExisteException(String message) {
        super(message);
    }
}
