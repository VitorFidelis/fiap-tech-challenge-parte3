package gateway.br.com.gateway.application.dto.enfermeiro;


import gateway.br.com.gateway.application.dto.usuarios.UsuarioInput;

public record EnfermeiroInputDTO(
        String nome,
        String email,
        String coren
) implements UsuarioInput {
}

