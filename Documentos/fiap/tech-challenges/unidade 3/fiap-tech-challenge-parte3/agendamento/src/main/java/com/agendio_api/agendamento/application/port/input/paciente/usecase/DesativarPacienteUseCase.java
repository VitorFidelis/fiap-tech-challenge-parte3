package com.agendio_api.agendamento.application.port.input.paciente.usecase;

import java.util.UUID;

public interface DesativarPacienteUseCase {
    void executar(UUID id);
}
