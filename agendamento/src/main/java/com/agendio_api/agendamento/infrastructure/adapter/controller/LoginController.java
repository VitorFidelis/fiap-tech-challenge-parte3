package com.agendio_api.agendamento.infrastructure.adapter.controller;

import com.agendio_api.agendamento.api.routes.RotasLogin;
import com.agendio_api.agendamento.application.port.dto.login.JwtTokenDTO;
import com.agendio_api.agendamento.application.port.dto.login.UserLoginDTO;
import com.agendio_api.agendamento.application.port.input.login.controller.LoginControllerInputPort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RotasLogin.AUTH)
public class LoginController {

    private final LoginControllerInputPort loginControllerInputPort;

    public LoginController(LoginControllerInputPort loginControllerInputPort) {
        this.loginControllerInputPort = loginControllerInputPort;
    }

    @PostMapping("/login")
    public JwtTokenDTO login(@RequestBody UserLoginDTO userLoginDTO) {
        return loginControllerInputPort.login(userLoginDTO);
    }
}
