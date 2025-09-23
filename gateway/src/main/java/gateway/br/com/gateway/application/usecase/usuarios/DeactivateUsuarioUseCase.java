package gateway.br.com.gateway.application.usecase.usuarios;

import gateway.br.com.gateway.infrastructure.persistence.adapter.usuarios.UsuarioRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeactivateUsuarioUseCase {
    private final UsuarioRepositoryImpl usuarioRepositoryImpl;

    public DeactivateUsuarioUseCase(final UsuarioRepositoryImpl usuarioRepositoryImpl) {
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;
    }

    public void execute(final UUID uuid) {
        var usuarioDomain = this.usuarioRepositoryImpl.deactivate(uuid);
        if (usuarioDomain.getAtivo() == true) {
            usuarioDomain.setAtivo(false);
        }
        this.usuarioRepositoryImpl.save(usuarioDomain);
    }
}
