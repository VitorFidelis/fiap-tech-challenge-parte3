package com.agendio_api.agendamento.application.port.input.medico.usecase;

import com.agendio_api.agendamento.application.port.dto.medico.CadastraMedicoDTO;
import com.agendio_api.agendamento.application.port.dto.medico.MedicoResponseDTO;

public interface CadastrarMedicoUseCase {
    MedicoResponseDTO executar(CadastraMedicoDTO cadastraMedicoDTO);
}
