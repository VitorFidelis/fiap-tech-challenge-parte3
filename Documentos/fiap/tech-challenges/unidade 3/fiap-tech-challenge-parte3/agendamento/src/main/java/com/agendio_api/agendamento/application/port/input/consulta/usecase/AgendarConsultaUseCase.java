package com.agendio_api.agendamento.application.port.input.consulta.usecase;

import com.agendio_api.agendamento.application.port.dto.consulta.AgendaConsultaDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;

public interface AgendarConsultaUseCase {
    ConsultaResponseDTO executar(AgendaConsultaDTO agendaConsultaDTO);
}
