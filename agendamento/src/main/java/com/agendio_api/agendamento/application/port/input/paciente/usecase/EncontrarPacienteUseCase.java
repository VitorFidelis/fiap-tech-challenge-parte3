package com.agendio_api.agendamento.application.port.input.paciente.usecase;

import com.agendio_api.agendamento.application.port.dto.paciente.PacienteResponseDTO;

import java.util.UUID;

public interface EncontrarPacienteUseCase {
    PacienteResponseDTO executar(UUID id);
}
