package gateway.br.com.gateway.application.dto.usuarios;

public record UsuarioInputDTO(
        String nome,
        String email
) implements UsuarioInput {
}
