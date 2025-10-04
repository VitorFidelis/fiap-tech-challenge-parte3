package com.agendio_api.agendamento.application.port.input.paciente.usecase;

import com.agendio_api.agendamento.application.port.dto.paciente.CadastraPacienteDTO;
import com.agendio_api.agendamento.application.port.dto.paciente.PacienteResponseDTO;

public interface CadastrarPacienteUseCase {
    PacienteResponseDTO executar(CadastraPacienteDTO cadastraPacienteDTO);


}
