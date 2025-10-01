package gateway.br.com.gateway.domain.model.valueobject;

import java.time.LocalDateTime;

public record HorarioConsulta(LocalDateTime inicio, LocalDateTime fim) {
    public HorarioConsulta {
        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("O início e fim do período não podem ser nulos");
        }
        if (!inicio.isBefore(fim)) {
            throw new IllegalArgumentException("O início do período deve ser antes do fim");
        }
    }

    public boolean contem(LocalDateTime dataHora) {
        return !dataHora.isBefore(inicio) && !dataHora.isAfter(fim);
    }

    public boolean sobrepoe(HorarioConsulta outroPeriodo) {
        return inicio.isBefore(outroPeriodo.fim) && fim.isAfter(outroPeriodo.inicio);
    }
}

