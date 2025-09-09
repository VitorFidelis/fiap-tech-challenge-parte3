package gateway.br.com.gateway.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import gateway.br.com.gateway.domain.exception.NomeTipoUsuarioInvalido;

public enum TipoUsuarioEnum {
    ADMIN,
    MEDICO,
    ENFERMEIRO,
    PACIENTE;

    @JsonCreator
    public static TipoUsuarioEnum fromString(String value) {
        if (value == null) {
            throw new NomeTipoUsuarioInvalido("Nome do tipo de usuário não pode ser nulo.");
        }
        try {
            return TipoUsuarioEnum.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new NomeTipoUsuarioInvalido("Nome do tipo de usuário inválido: " + value);
        }
    }

    @JsonValue
    public String toValue() {
        return this.name().toLowerCase(); // devolve em minúsculo no JSON
    }
}
