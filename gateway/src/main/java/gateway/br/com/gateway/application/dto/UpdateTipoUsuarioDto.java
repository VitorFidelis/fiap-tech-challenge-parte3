package gateway.br.com.gateway.application.dto;

import gateway.br.com.gateway.domain.model.TipoUsuarioEnum;

public record UpdateTipoUsuario(
        TipoUsuarioEnum nome,
        String descricao
) {

}
