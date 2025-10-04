package com.agendio_api.agendamento.application.port.dto.medico;

import com.agendio_api.agendamento.application.port.dto.usuario.UsuarioRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AtualizaMedicoDTO(
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @Email(message = "Formato de e-mail inválido")
        @Size(max = 100, message = "O e-mail não pode ter mais de 100 caracteres")
        String email,

        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).+$",
                message = "A senha deve conter letras e números"
        )
        String senha,

        @Pattern(
                regexp = "CRM/[A-Z]{2}\\s\\d{4,6}",
                message = "Formato de CRM inválido. Use o formato 'CRM/UF 123456'"
        )
        String crm,

        @Size(max = 100, message = "A especialidade não pode ter mais de 100 caracteres")
        String especialidade) implements UsuarioRequest {
}