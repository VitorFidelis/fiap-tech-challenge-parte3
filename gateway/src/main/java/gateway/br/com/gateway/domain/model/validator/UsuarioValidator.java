package gateway.br.com.gateway.domain.model.validator;


import gateway.br.com.gateway.domain.model.usuario.Usuario;

public interface UsuarioValidator extends BaseEntityValidator {
    void validar(Usuario usuario);
}
