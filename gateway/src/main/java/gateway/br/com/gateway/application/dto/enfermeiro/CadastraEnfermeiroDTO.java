package gateway.br.com.gateway.application.dto.enfermeiro;

import gateway.br.com.gateway.application.dto.usuarios.UsuarioInput;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO para cadastro de um novo enfermeiro.
 * Segue SRP e atua apenas como portador de dados (Application Layer).
 *
 * Validações garantem integridade da entrada:
 * - Nome: obrigatório, entre 3 e 100 caracteres
 * - Email: obrigatório, válido, único, máx. 100 caracteres
 * - Senha: obrigatória, mínimo 8 caracteres, deve conter letras e números
 * - COREN: obrigatório, formato XX.XXX.XX-XX, único no sistema
 */
public record CadastraEnfermeiroDTO(
        @NotBlank(message = "nome deve ser informado")
        @Size(min = 3, max = 100, message = "nome deve estar entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "email deve ser informado")
        @Email(message = "email deve ser válido")
        @Size(max = 100, message = "email deve ter no máximo 100 caracteres")
        String email,

        @NotBlank(message = "senha deve ser informada")
        @Size(min = 8, message = "senha deve ter no mínimo 8 caracteres")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$",
                message = "senha deve conter letras e números"
        )
        String senha,

        @NotBlank(message = "coren deve ser informado")
        @Pattern(
                regexp = "\\d{2}\\.\\d{3}\\.\\d{2}-[A-Za-z0-9]",
                message = "coren deve estar no formato XX.XXX.XX-X"
        )
        String coren
) implements UsuarioInput {

    /**
     * Construtor que garante que os campos estejam no formato correto.
     * Remove espaços em branco extras e formata o COREN.
     */
    public CadastraEnfermeiroDTO {
        if (nome != null) {
            nome = nome.trim();
        }
        if (email != null) {
            email = email.trim().toLowerCase();
        }
    }
}
