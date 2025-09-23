package gateway.br.com.gateway.domain.exception;

public class TokenJwtInvalidoOuExpirado extends RuntimeException {
    public TokenJwtInvalidoOuExpirado(String message) {
        super(message);
    }
}
