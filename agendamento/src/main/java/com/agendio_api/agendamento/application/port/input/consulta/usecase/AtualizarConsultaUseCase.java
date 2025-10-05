package com.agendio_api.agendamento.application.port.input.consulta.usecase;

import com.agendio_api.agendamento.application.port.dto.consulta.AtualizaConsultaDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;

import java.util.UUID;

public interface AtualizarConsultaUseCase {
    ConsultaResponseDTO executar(UUID id, AtualizaConsultaDTO atualizaConsultaDTO);
}
