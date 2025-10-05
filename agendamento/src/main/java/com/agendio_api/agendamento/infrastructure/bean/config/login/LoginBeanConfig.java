package com.agendio_api.agendamento.infrastructure.bean.config.login;

import com.agendio_api.agendamento.application.controller.LoginControllerInputPortImpl;
import com.agendio_api.agendamento.application.port.input.login.controller.LoginControllerInputPort;
import com.agendio_api.agendamento.application.port.input.login.usecase.LoginUsuarioUseCase;
import com.agendio_api.agendamento.application.usecase.login.LoginUsuarioUseCaseImpl;
import com.agendio_api.agendamento.infrastructure.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class LoginBeanConfig {

    @Bean
    LoginUsuarioUseCase registerLoginUsuarioUseCase(AuthenticationManager authenticationManager,
                                                    JwtTokenProvider jwtTokenProvider){
        return new LoginUsuarioUseCaseImpl(authenticationManager,jwtTokenProvider);
    }

    @Bean
    LoginControllerInputPort registerLoginControllerInputPort(LoginUsuarioUseCase loginUsuarioUseCase){
        return new LoginControllerInputPortImpl(loginUsuarioUseCase);
    }
}
