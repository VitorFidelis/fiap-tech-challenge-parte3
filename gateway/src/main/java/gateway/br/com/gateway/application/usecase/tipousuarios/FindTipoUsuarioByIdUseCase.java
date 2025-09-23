package gateway.br.com.gateway.application.usecase.tipousuarios;

import gateway.br.com.gateway.domain.exception.IdTipoUsuarioInvalido;
import gateway.br.com.gateway.domain.model.tipousuarios.TipoUsuario;
import gateway.br.com.gateway.infrastructure.persistence.adapter.tipousuarios.TipoUsuarioRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindTipoUsuarioByIdUseCase {
    private final TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl;

    public FindTipoUsuarioByIdUseCase(
            final TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl
    ) {
        this.tipoUsuarioRepositoryImpl = tipoUsuarioRepositoryImpl;
    }

    public TipoUsuario execute(final Long id) {
        try {
            return this.tipoUsuarioRepositoryImpl.findById(id);
        }catch (EntityNotFoundException ex) {
            throw new IdTipoUsuarioInvalido("ID: " + id + " não encontrado");
        }
    }
}
