package gateway.br.com.gateway.domain.model.valueobject;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Getter
@Embeddable
public class Coren {

    private String value;

    private Coren(String value) {
        this.value = format(value);
    }

    protected Coren() {
    }

    public static Coren of(String value) {
        Objects.requireNonNull(value, "COREN não pode ser nulo");
        return new Coren(value);
    }

    private String format(String raw) {
        String digits = raw.replaceAll("[^A-Za-z0-9]", "");
        if (digits.length() >= 7) {
            return String.format("%s.%s.%s-%s",
                    digits.substring(0, 2),
                    digits.substring(2, 5),
                    digits.substring(5, 7),
                    digits.substring(7, 8));
        }
        throw new IllegalArgumentException("Formato inválido para COREN: " + raw);
    }

}

