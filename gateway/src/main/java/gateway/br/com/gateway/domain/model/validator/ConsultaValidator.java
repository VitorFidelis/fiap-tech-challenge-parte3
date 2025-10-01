package gateway.br.com.gateway.domain.model.validator;


import gateway.br.com.gateway.domain.model.consulta.Consulta;

public interface ConsultaValidator extends BaseEntityValidator {
    void validar(Consulta consulta);
}
