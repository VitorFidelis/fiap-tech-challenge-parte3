package com.agendio_api.agendamento.application.port.input.login.usecase;

import com.agendio_api.agendamento.application.port.dto.login.JwtTokenDTO;

public interface LoginUsuarioUseCase {
    JwtTokenDTO executar(String login, String password);
}
