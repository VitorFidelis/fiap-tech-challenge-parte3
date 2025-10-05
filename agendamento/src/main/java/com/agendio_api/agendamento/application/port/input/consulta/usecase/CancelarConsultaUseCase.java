package com.agendio_api.agendamento.application.port.input.consulta.usecase;

import java.util.UUID;

public interface CancelarConsultaUseCase {
    void executar(UUID id, String motivoCancelamento);

}
