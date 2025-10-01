package gateway.br.com.gateway.application.dto.enfermeiro;

import gateway.br.com.gateway.application.dto.usuarios.UsuarioInput;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO para atualização de um enfermeiro existente.
 * Segue SRP e atua apenas como portador de dados (Application Layer).
 * <p>
 * Validações garantem integridade da entrada:
 * - Nome: obrigatório, entre 3 e 100 caracteres
 * - Email: obrigatório, válido, máx. 100 caracteres
 * - COREN: obrigatório, regex de formato
 */
public record AtualizaEnfermeiroDTO(

        @NotBlank(message = "nome deve ser informado")
        @Size(min = 3, max = 100, message = "nome deve estar entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "email deve ser informado")
        @Email(message = "email deve ser válido")
        @Size(max = 100, message = "email deve ter no máximo 100 caracteres")
        String email,

        @NotBlank(message = "coren deve ser informado")
        @Pattern(
                regexp = "\\d{2}\\.\\d{3}\\.\\d{2}-[A-Za-z0-9]",
                message = "coren deve estar no formato XX.XXX.XX-X"
        )
        String coren

) implements UsuarioInput {
}
