package gateway.br.com.gateway.presentation.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import gateway.br.com.gateway.domain.exception.IdTipoUsuarioInvalido;
import gateway.br.com.gateway.domain.exception.NomeTipoUsuarioInvalido;
import gateway.br.com.gateway.domain.exception.TokenJwtInvalidoOuExpirado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NomeTipoUsuarioInvalido.class)
    public ResponseEntity<Object> handlerNomeTipoUsuarioInvalidoException(NomeTipoUsuarioInvalido ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("ERRO:" + " \n " + "Argumento de entrada inválido [NOME]:" + "\n " + ex.getMessage());
    }

    @ExceptionHandler(IdTipoUsuarioInvalido.class)
    public ResponseEntity<Object> handlerIdTipoUsuarioInvalido(IdTipoUsuarioInvalido ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("ERRO:" + " \n " + "Recurso solicitado não encontrado [ID]:" + "\n " + ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handlerBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!");
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity handlerAuthenticationServiceException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação!");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handlerAccessDeniedException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado!");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
    }

    @ExceptionHandler(TokenJwtInvalidoOuExpirado.class)
    public ResponseEntity<Object> handlerTokenJwtInvalidoOuExpirado(TokenJwtInvalidoOuExpirado ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("ERRO:" + ex.getLocalizedMessage());
    }
}
