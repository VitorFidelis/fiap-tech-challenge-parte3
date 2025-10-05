package com.agendio_api.agendamento.application.port.dto.paciente;

import com.agendio_api.agendamento.application.port.dto.usuario.UsuarioRequest;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CadastraPacienteDTO(
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


        @NotNull(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento deve ser uma data passada")
        LocalDate dataNascimento
) implements UsuarioRequest {
}
