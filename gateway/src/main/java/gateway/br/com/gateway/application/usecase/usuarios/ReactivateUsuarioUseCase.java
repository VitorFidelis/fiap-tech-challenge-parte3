package gateway.br.com.gateway.application.usecase.usuarios;

import gateway.br.com.gateway.infrastructure.persistence.adapter.usuarios.UsuarioRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReactivateUsuarioUseCase {
    private final UsuarioRepositoryImpl usuarioRepositoryImpl;

    public ReactivateUsuarioUseCase(final UsuarioRepositoryImpl usuarioRepositoryImpl) {
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;
    }

    public void execute(final UUID uuid) {
        var usuarioDomain  = this.usuarioRepositoryImpl.findById(uuid);
        if (usuarioDomain.getAtivo() == false) {
            usuarioDomain.setAtivo(true);
        }
        this.usuarioRepositoryImpl.save(usuarioDomain);
    }
}
