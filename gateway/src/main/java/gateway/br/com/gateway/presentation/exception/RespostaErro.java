package gateway.br.com.gateway.presentation.exception;

import java.time.LocalDateTime;

public record RespostaErro(int status, String mensagem, LocalDateTime timestamp) {
}
