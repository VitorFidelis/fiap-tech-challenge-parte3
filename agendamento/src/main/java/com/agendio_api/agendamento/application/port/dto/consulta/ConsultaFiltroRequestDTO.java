package com.agendio_api.agendamento.application.port.dto.consulta;

import java.time.LocalDate;
import java.util.UUID;

public record ConsultaFiltroRequestDTO(
        UUID medicoId,
        LocalDate inicio,
        LocalDate fim
) {
}

