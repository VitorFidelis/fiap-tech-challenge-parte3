package gateway.br.com.gateway.presentation.api.usuarios;

public record UsuarioRequest(
        String nome,
        String sobrenome,
        String email,
        String senha,
        String tipo
) {
}
