package gateway.br.com.gateway.application.usecase;

import gateway.br.com.gateway.infrastructure.persistence.adapter.TipoUsuarioRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class DeactivateTipoUsuarioByIdUseCase {
    private final TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl;

    public DeactivateTipoUsuarioByIdUseCase(TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl) {
        this.tipoUsuarioRepositoryImpl = tipoUsuarioRepositoryImpl;
    }

    public Boolean deactivate(Long id) {
       var tipoUsuario = this.tipoUsuarioRepositoryImpl.dectivate(id);
       if (tipoUsuario.getAtivo() == true){
           tipoUsuario.setAtivo(false);
       }
       return this.tipoUsuarioRepositoryImpl.save(tipoUsuario).getAtivo().booleanValue();
    }
}
