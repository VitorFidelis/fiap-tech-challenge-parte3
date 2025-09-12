package gateway.br.com.gateway.domain.repository.usuarios;

import gateway.br.com.gateway.domain.model.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Usuario update(Usuario usuario);
    Usuario deactivate(UUID uuid);
    Usuario reactivate(UUID uuid);
    Usuario findById(UUID uuid);
    Page<Usuario> findAll(Pageable pageable);
    Usuario findByEmail(String email);
}
