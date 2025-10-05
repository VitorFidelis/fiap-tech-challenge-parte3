package com.agendio_api.agendamento.application.controller;

import com.agendio_api.agendamento.application.port.dto.login.JwtTokenDTO;
import com.agendio_api.agendamento.application.port.dto.login.UserLoginDTO;
import com.agendio_api.agendamento.application.port.input.login.controller.LoginControllerInputPort;
import com.agendio_api.agendamento.application.port.input.login.usecase.LoginUsuarioUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginControllerInputPortImpl implements LoginControllerInputPort {

    private static final Logger logger = LoggerFactory.getLogger(LoginControllerInputPortImpl.class.getName());

    private final LoginUsuarioUseCase loginUsuarioUseCase;

    public LoginControllerInputPortImpl(LoginUsuarioUseCase loginUsuarioUseCase) {
        this.loginUsuarioUseCase = loginUsuarioUseCase;
    }

    @Override
    public JwtTokenDTO login(UserLoginDTO userLoginDTO) {
        logger.info("Iniciando login de {}", userLoginDTO.login());
        return loginUsuarioUseCase.executar(userLoginDTO.login(),  userLoginDTO.password());
    }
}
