package com.agendio_api.agendamento.application.port.input.enfermeiro.usecase;

import com.agendio_api.agendamento.application.port.dto.enfermeiro.CadastraEnfermeiroDTO;
import com.agendio_api.agendamento.application.port.dto.enfermeiro.EnfermeiroResponseDTO;

public interface CadastrarEnfermeiroUseCase {
    EnfermeiroResponseDTO execute(CadastraEnfermeiroDTO cadastraEnfermeiroDTO);
}
