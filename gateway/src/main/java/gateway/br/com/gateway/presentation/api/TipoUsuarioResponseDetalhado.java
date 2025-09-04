package gateway.br.com.gateway.presentation.api;

import gateway.br.com.gateway.domain.model.TipoUsuarioEnum;

public record TipoUsuarioResponseDetalhado(
        Long id,
        TipoUsuarioEnum nome,
        String descricao,
        Boolean ativo
) {

}
