package com.agendio_api.agendamento.application.port.input.enfermeiro.usecase;

import java.util.UUID;

public interface ReativarEnfermeiroUseCase {
    void executar(UUID id);
}
