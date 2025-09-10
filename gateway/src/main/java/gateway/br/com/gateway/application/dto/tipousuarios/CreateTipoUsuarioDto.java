package gateway.br.com.gateway.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import gateway.br.com.gateway.domain.model.TipoUsuarioEnum;

public record CreateTipoUsuarioDto(
        TipoUsuarioEnum nome,
        String descricao
) {
    @JsonCreator
    public CreateTipoUsuarioDto(
            @JsonProperty("nome") String nome,
            @JsonProperty("descricao") String descricao
    ) {
        this(TipoUsuarioEnum.fromString(nome), descricao);
    }
}
