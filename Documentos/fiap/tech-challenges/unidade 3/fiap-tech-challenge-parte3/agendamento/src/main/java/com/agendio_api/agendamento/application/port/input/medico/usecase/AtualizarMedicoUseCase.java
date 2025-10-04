package com.agendio_api.agendamento.application.port.input.medico.usecase;

import com.agendio_api.agendamento.application.port.dto.medico.AtualizaMedicoDTO;
import com.agendio_api.agendamento.application.port.dto.medico.MedicoResponseDTO;

import java.util.UUID;

public interface AtualizarMedicoUseCase {
    MedicoResponseDTO executar(UUID id, AtualizaMedicoDTO atualizaMedicoDTO);
}
