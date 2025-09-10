package gateway.br.com.gateway.domain.repository;

import gateway.br.com.gateway.domain.model.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Usuario update(Usuario usuario);
    Boolean deactivate(UUID uuid);
    Boolean reactivate(UUID uuid);
    Usuario findById(UUID uuid);
    List<Usuario> findAll();
}
