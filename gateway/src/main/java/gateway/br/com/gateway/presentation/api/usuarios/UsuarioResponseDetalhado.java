package gateway.br.com.gateway.presentation.api.usuarios;

import gateway.br.com.gateway.domain.model.tipousuarios.TipoUsuario;

import java.util.UUID;

public record UsuarioResponseDetalhado(
        UUID id,
        String nome,
        String sobrenome,
        String email,
        String senha,
        Boolean ativo,
        TipoUsuario tipoUsuario
) {
}
