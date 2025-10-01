package gateway.br.com.gateway.domain.model.valueobject;

import java.time.LocalDate;

/**
 * Esta classe representa um período de datas em que um médico tem consultas agendadas.
 * Ela é usada para encontrar consultas de um médico em uma determinada data.
 * A classe é um record, que é uma forma de classe em Java que remove a necessidade de um construtor e getters e setters.
 * O record tem um construtor que valida que o início do período é antes do fim e que não é nulo.
 * A classe também tem um método que retorna se uma data está dentro do período.
 * E outro método que retorna se o período atual sobrepõe outro período.
 *
 */
public record PeriodoConsultas(LocalDate inicio, LocalDate fim) {
    public PeriodoConsultas {
        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("O início e fim do período não podem ser nulos");
        }
        if (!inicio.isBefore(fim)) {
            throw new IllegalArgumentException("O início do período deve ser antes do fim");
        }
    }

    public boolean contem(LocalDate data) {
        return !data.isBefore(inicio) && !data.isAfter(fim);
    }

    public boolean sobrepoe(PeriodoConsultas outroPeriodo) {
        return inicio.isBefore(outroPeriodo.fim) && fim.isAfter(outroPeriodo.inicio);
    }
}

