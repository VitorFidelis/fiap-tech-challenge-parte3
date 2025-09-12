package gateway.br.com.gateway.application.usecase.tipousuarios;

import gateway.br.com.gateway.application.dto.tipousuarios.UpdateTipoUsuarioDto;
import gateway.br.com.gateway.domain.exception.IdTipoUsuarioInvalido;
import gateway.br.com.gateway.domain.model.tipousuarios.TipoUsuario;
import gateway.br.com.gateway.infrastructure.persistence.adapter.tipousuarios.TipoUsuarioRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateTipoUsuarioUseCase {
    private final TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl;

    public UpdateTipoUsuarioUseCase(final TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl) {
        this.tipoUsuarioRepositoryImpl = tipoUsuarioRepositoryImpl;
    }

    public TipoUsuario execute(Long id, UpdateTipoUsuarioDto updateTipoUsuarioDto) {
        try{
            var tipoUsuario = this.tipoUsuarioRepositoryImpl.findById(id);
            tipoUsuario.setNome(updateTipoUsuarioDto.nome());
            tipoUsuario.setDescricao(updateTipoUsuarioDto.descricao());
            return this.tipoUsuarioRepositoryImpl.save(tipoUsuario);
        }catch (EntityNotFoundException ex){
            throw new IdTipoUsuarioInvalido("Tipo de usuário com id [" + id + "] não encontrado.");
        }
    }
}
