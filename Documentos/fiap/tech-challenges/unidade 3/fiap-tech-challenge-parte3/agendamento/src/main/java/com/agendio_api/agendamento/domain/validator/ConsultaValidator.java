package com.agendio_api.agendamento.domain.validator;

import com.agendio_api.agendamento.domain.model.consulta.Consulta;

public interface ConsultaValidator extends BaseEntityValidator {
    void validar(Consulta consulta);
}
