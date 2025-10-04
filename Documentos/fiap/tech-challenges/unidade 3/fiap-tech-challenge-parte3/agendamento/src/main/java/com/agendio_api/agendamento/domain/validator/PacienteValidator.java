package com.agendio_api.agendamento.domain.validator;

import com.agendio_api.agendamento.domain.model.usuario.Paciente;

public interface PacienteValidator extends UsuarioValidator {
    void validar(Paciente paciente);
}
