package gateway.br.com.gateway.application.usecase.usuarios;

import gateway.br.com.gateway.domain.model.usuario.Usuario;
import gateway.br.com.gateway.infrastructure.persistence.adapter.usuarios.UsuarioRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindUsuarioByIdUseCase {
    private final UsuarioRepositoryImpl usuarioRepositoryImpl;

    public FindUsuarioByIdUseCase(UsuarioRepositoryImpl usuarioRepositoryImpl) {
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;
    }

    public Usuario execute(UUID uuid) {
        return this.usuarioRepositoryImpl.findById(uuid);
    }
}
