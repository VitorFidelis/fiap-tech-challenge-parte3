package gateway.br.com.gateway.application.dto.paciente;

import gateway.br.com.gateway.application.dto.usuarios.UsuarioInput;

import java.util.UUID;

public record PacienteInputDTO(
        UUID id,
        String nome,
        String email
) implements UsuarioInput {
}

