package gateway.br.com.gateway.presentation.api.tipousuarios;

public record TipoUsuarioResponseDetalhado(
        Long id,
        String nome,
        String descricao,
        Boolean ativo
) {

}
