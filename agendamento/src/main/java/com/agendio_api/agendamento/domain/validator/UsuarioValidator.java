package com.agendio_api.agendamento.domain.validator;

import com.agendio_api.agendamento.domain.model.usuario.Usuario;

public interface UsuarioValidator extends BaseEntityValidator {
    void validar(Usuario usuario);
}
