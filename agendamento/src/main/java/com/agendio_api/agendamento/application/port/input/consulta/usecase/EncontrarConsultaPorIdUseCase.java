package com.agendio_api.agendamento.application.port.input.consulta.usecase;

import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;

import java.util.UUID;

public interface EncontrarConsultaPorIdUseCase {
    ConsultaResponseDTO executar(UUID id);
}
