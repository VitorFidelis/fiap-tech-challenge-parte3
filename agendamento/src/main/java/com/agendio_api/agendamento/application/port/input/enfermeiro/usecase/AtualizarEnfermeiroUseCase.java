package com.agendio_api.agendamento.application.port.input.enfermeiro.usecase;

import com.agendio_api.agendamento.application.port.dto.enfermeiro.AtualizaEnfermeiroDTO;
import com.agendio_api.agendamento.application.port.dto.enfermeiro.EnfermeiroResponseDTO;

import java.util.UUID;

public interface AtualizarEnfermeiroUseCase {
    EnfermeiroResponseDTO execute(UUID id, AtualizaEnfermeiroDTO atualizaEnfermeiroDTO);
}
