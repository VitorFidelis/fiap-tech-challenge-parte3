package com.agendio_api.agendamento.application.port.input.medico.usecase;

import com.agendio_api.agendamento.application.port.dto.medico.MedicoResponseDTO;

public interface EncontrarMedicoPorCRMUseCase {
    MedicoResponseDTO executar(String crm);
}
