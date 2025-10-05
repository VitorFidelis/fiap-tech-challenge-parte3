package com.agendio_api.agendamento.domain.validator;

import com.agendio_api.agendamento.domain.model.usuario.Enfermeiro;

public interface EnfermeiroValidator extends UsuarioValidator {
    void validar(Enfermeiro enfermeiro);

}
