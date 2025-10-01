package gateway.br.com.gateway.application.dto.paciente;

import gateway.br.com.gateway.application.dto.usuarios.UsuarioInput;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AtualizaPacienteDTO(
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @Email(message = "Formato de e-mail inválido")
        @Size(max = 100, message = "O e-mail não pode ter mais de 100 caracteres")
        String email,

        @Past(message = "A data de nascimento deve ser uma data passada")
        LocalDate dataNascimento
) implements UsuarioInput {
}
