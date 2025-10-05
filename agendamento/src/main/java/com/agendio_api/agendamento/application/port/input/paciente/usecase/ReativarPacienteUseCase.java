package com.agendio_api.agendamento.application.port.input.paciente.usecase;

import java.util.UUID;

public interface ReativarPacienteUseCase {
    void executar(UUID id);
}
