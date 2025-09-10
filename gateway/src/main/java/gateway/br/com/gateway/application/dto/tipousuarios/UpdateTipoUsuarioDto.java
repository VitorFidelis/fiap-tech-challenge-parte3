package gateway.br.com.gateway.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import gateway.br.com.gateway.domain.model.TipoUsuarioEnum;

public record UpdateTipoUsuarioDto(
        TipoUsuarioEnum nome,
        String descricao
) {
    @JsonCreator
    public UpdateTipoUsuarioDto(
            @JsonProperty("nome") String nome,
            @JsonProperty("descricao") String descricao
    ) {
        this(TipoUsuarioEnum.fromString(nome), descricao);
    }
}
