package com.agendio_api.agendamento.application.port.input.medico.usecase;

import java.util.UUID;

public interface DesativarMedicoUseCase {
    void executar(UUID id);
}
