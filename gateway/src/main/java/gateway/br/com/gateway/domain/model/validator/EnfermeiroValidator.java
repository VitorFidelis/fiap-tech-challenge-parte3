package gateway.br.com.gateway.domain.model.validator;


import gateway.br.com.gateway.domain.model.usuario.Enfermeiro;

public interface EnfermeiroValidator extends UsuarioValidator {
    void validar(Enfermeiro enfermeiro);

}
