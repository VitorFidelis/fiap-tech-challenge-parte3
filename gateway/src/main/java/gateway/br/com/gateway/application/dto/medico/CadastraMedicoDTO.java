package gateway.br.com.gateway.application.dto.medico;

import gateway.br.com.gateway.application.dto.usuarios.UsuarioInput;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CadastraMedicoDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        @Size(max = 100, message = "O e-mail não pode ter mais de 100 caracteres")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).+$",
                message = "A senha deve conter letras e números"
        )
        String senha,

        @NotBlank(message = "O CRM é obrigatório")
        @Pattern(
                regexp = "CRM/[A-Z]{2}\\s\\d{4,6}",
                message = "Formato de CRM inválido. Use o formato 'CRM/UF 123456'"
        )
        String crm,

        @NotBlank(message = "A especialidade é obrigatória")
        @Size(max = 100, message = "A especialidade não pode ter mais de 100 caracteres")
        String especialidade
) implements UsuarioInput {
}
