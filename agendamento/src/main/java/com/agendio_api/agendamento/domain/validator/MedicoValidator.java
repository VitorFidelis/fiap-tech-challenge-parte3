package com.agendio_api.agendamento.domain.validator;

import com.agendio_api.agendamento.domain.model.usuario.Medico;

public interface MedicoValidator extends UsuarioValidator {
    void validar(Medico medico);
}
