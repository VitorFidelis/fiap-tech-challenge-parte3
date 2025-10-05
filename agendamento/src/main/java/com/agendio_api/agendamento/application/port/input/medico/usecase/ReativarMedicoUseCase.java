package com.agendio_api.agendamento.application.port.input.medico.usecase;

import java.util.UUID;

public interface ReativarMedicoUseCase {
    void executar(UUID id);
}
