package gateway.br.com.gateway.application.dto;

import gateway.br.com.gateway.domain.model.TipoUsuario;
import gateway.br.com.gateway.domain.model.TipoUsuarioEnum;

public record CreateTipoUsuarioDto(
        TipoUsuarioEnum nome,
        String descricao,
        Boolean ativo
) {
    public CreateTipoUsuarioDto(TipoUsuarioEnum tipoUsuarioEnum, TipoUsuario tipoUsuario) {
        this(
                tipoUsuarioEnum,
                tipoUsuario.getDescricao(),
                tipoUsuario.getAtivo()
        );
    }
}
