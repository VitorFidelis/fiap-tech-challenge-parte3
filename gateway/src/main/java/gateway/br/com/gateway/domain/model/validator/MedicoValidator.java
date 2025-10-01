package gateway.br.com.gateway.domain.model.validator;


import gateway.br.com.gateway.domain.model.usuario.Medico;

public interface MedicoValidator extends UsuarioValidator {
    void validar(Medico medico);
}
