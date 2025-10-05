package com.agendio_api.agendamento.application.port.input.paciente.usecase;

import com.agendio_api.agendamento.application.port.dto.paciente.AtualizaPacienteDTO;
import com.agendio_api.agendamento.application.port.dto.paciente.PacienteResponseDTO;

import java.util.UUID;

public interface AtualizarPacienteUseCase {
    PacienteResponseDTO executar(UUID id, AtualizaPacienteDTO atualizaPacienteDTO);
}
