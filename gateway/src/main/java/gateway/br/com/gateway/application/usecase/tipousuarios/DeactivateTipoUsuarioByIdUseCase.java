package gateway.br.com.gateway.application.usecase.tipousuarios;

import gateway.br.com.gateway.infrastructure.persistence.adapter.tipousuarios.TipoUsuarioRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class DeactivateTipoUsuarioByIdUseCase {
    private final TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl;

    public DeactivateTipoUsuarioByIdUseCase(TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl) {
        this.tipoUsuarioRepositoryImpl = tipoUsuarioRepositoryImpl;
    }

    public void deactivate(Long id) {
       var tipoUsuario = this.tipoUsuarioRepositoryImpl.dectivate(id);
       if (tipoUsuario.getAtivo() == true){
           tipoUsuario.setAtivo(false);
       }
       this.tipoUsuarioRepositoryImpl.save(tipoUsuario);
    }
}
