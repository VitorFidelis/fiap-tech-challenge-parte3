package gateway.br.com.gateway.domain.repository;

import gateway.br.com.gateway.domain.model.Usuario;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Usuario update(Usuario usuario);
    Boolean deactivate(UUID uuid);
    Boolean reactivate(UUID uuid);
    Usuario findById(UUID uuid);
    Page<Usuario> findAll();
}
