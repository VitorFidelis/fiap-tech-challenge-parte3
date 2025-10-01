package gateway.br.com.gateway.domain.exception;


public class EmailJaExisteException extends RuntimeException {

    public EmailJaExisteException(String message) {
        super(message);
    }
}
