package gateway.br.com.gateway.application.dto.consulta;

import java.time.LocalDate;
import java.util.UUID;

public record ConsultaFiltroRequestDTO(
        UUID medicoId,
        LocalDate inicio,
        LocalDate fim
) {
}

