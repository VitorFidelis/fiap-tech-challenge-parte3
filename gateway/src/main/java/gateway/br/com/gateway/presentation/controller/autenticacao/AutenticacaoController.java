package gateway.br.com.gateway.presentation.controller.autenticacao;

import gateway.br.com.gateway.presentation.api.autenticacao.AutenticacaoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    private final AuthenticationManager authenticationManager;

    public AutenticacaoController(
            final AuthenticationManager authenticationManager
    ) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity efetuarLogin(
            @RequestBody final AutenticacaoRequest autenticacaoRequest
    ) {
        var token = new UsernamePasswordAuthenticationToken(
                autenticacaoRequest.email(),
                autenticacaoRequest.senha()
        );
        var authenticate = authenticationManager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
