package com.agendio_api.agendamento.application.port.input.login.controller;

import com.agendio_api.agendamento.application.port.dto.login.JwtTokenDTO;
import com.agendio_api.agendamento.application.port.dto.login.UserLoginDTO;

public interface LoginControllerInputPort {

    JwtTokenDTO login(UserLoginDTO userLoginDTO);
}
