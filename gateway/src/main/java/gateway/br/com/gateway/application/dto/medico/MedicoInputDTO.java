package gateway.br.com.gateway.application.dto.medico;


import gateway.br.com.gateway.application.dto.usuarios.UsuarioInput;

public record MedicoInputDTO(
        String nome,
        String email,
        String crm
) implements UsuarioInput {
}

