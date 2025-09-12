package gateway.br.com.gateway.application.usecase.usuarios;

import gateway.br.com.gateway.domain.model.usuario.Usuario;
import gateway.br.com.gateway.infrastructure.persistence.adapter.usuarios.UsuarioRepositoryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindUsuarioAllUseCase {
    private final UsuarioRepositoryImpl usuarioRepositoryImpl;

    public FindUsuarioAllUseCase(UsuarioRepositoryImpl usuarioRepositoryImpl) {
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;
    }

    public Page<Usuario> execute(Pageable pageable) {
        return this.usuarioRepositoryImpl.findAll(pageable);
    }
}
