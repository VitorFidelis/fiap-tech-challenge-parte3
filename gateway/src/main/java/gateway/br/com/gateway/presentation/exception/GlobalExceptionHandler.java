package gateway.br.com.gateway.presentation.exception;

import gateway.br.com.gateway.domain.exception.IdTipoUsuarioInvalido;
import gateway.br.com.gateway.domain.exception.NomeTipoUsuarioInvalido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
