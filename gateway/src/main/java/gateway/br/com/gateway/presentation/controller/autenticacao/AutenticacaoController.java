package gateway.br.com.gateway.presentation.controller.autenticacao;

import gateway.br.com.gateway.infrastructure.security.JwtService;
import gateway.br.com.gateway.infrastructure.security.UserDetailsImpl;

import gateway.br.com.gateway.presentation.api.autenticacao.AutenticacaoRequest;
import gateway.br.com.gateway.presentation.api.autenticacao.DadosTokenJWT;

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
    private final JwtService jwtService;

    public AutenticacaoController(
            final AuthenticationManager authenticationManager,
            final JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity efetuarLogin(
            @RequestBody final AutenticacaoRequest autenticacaoRequest
    ) {
        // criando um objeto de autenticação com email e senha
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                autenticacaoRequest.email(),
                autenticacaoRequest.senha()
        );

        // aqui o Spring vai chamar o UserDetailsService e o PasswordEncoder para validar usuário e senha
        var authentication = authenticationManager.authenticate(authenticationToken);

        // pega objeto UserDetails que contém os dados
        var userDetails = (UserDetailsImpl) authentication.getPrincipal();
        var usuario = userDetails.getUsuario();

        // gera o token JWT baseado nos dados do usuário(Decoded Header, Decoded Payload)
        var generateToken = jwtService.generateToken(usuario);

        // retorna o token encapsulado em um DTO para o cliente
        return ResponseEntity.ok(new DadosTokenJWT(generateToken));
    }
}
