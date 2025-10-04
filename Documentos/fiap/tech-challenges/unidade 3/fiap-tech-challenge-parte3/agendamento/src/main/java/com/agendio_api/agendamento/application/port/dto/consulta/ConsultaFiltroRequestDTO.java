package com.agendio_api.agendamento.application.port.dto.consulta;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaFiltroRequestDTO(
        UUID medicoId,
        LocalDateTime inicio,
        LocalDateTime fim
) {
}

