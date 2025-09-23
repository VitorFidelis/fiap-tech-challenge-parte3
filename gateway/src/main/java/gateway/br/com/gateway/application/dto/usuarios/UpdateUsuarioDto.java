package gateway.br.com.gateway.application.dto.usuarios;

public record UpdateUsuarioDto(
        String nome,
        String sobrenome,
        String email,
        String senha,
        String tipo
) {

}
