package gateway.br.com.gateway.application.usecase.tipousuarios;

import gateway.br.com.gateway.domain.model.tipousuarios.TipoUsuario;
import gateway.br.com.gateway.infrastructure.persistence.adapter.tipousuarios.TipoUsuarioRepositoryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FIndTipoUsuarioAllUseCase {
    private final TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl;

    public FIndTipoUsuarioAllUseCase(final TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl) {
        this.tipoUsuarioRepositoryImpl = tipoUsuarioRepositoryImpl;
    }

    public Page<TipoUsuario> execute(Pageable pageable) {
        return this.tipoUsuarioRepositoryImpl.findAll(pageable);
    }

}
