package com.agendio_api.agendamento.application.usecase.login;

import com.agendio_api.agendamento.application.port.dto.login.JwtTokenDTO;
import com.agendio_api.agendamento.application.port.input.login.usecase.LoginUsuarioUseCase;
import com.agendio_api.agendamento.infrastructure.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class LoginUsuarioUseCaseImpl implements LoginUsuarioUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginUsuarioUseCaseImpl(AuthenticationManager authenticationManager,
                                   JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public JwtTokenDTO executar(String login, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );

        String token = jwtTokenProvider.generateToken(authentication);
        return new JwtTokenDTO(token);
    }
}
