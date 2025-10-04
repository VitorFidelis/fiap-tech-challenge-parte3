package com.agendio_api.agendamento.application.port.input.enfermeiro.usecase;

import com.agendio_api.agendamento.application.port.dto.enfermeiro.EnfermeiroResponseDTO;

import java.util.UUID;

public interface EncontrarEnfermeiroUseCase {
    EnfermeiroResponseDTO execute(UUID id);
}
