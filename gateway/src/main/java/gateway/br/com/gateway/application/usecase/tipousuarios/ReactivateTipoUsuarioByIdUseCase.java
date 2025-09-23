package gateway.br.com.gateway.application.usecase.tipousuarios;

import gateway.br.com.gateway.infrastructure.persistence.adapter.tipousuarios.TipoUsuarioRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class ReactivateTipoUsuarioByIdUseCase {
    private final TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl;

    public ReactivateTipoUsuarioByIdUseCase(TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl) {
        this.tipoUsuarioRepositoryImpl = tipoUsuarioRepositoryImpl;
    }

    public void execute(Long id) {
        var tipoUsuario  = this.tipoUsuarioRepositoryImpl.reactivate(id);
        if (tipoUsuario.getAtivo() == false) {
            tipoUsuario.setAtivo(true);
        }
        this.tipoUsuarioRepositoryImpl.save(tipoUsuario);
    }
}
