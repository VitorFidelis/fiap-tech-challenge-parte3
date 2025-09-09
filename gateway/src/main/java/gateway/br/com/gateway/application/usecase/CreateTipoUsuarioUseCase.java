package gateway.br.com.gateway.application.usecase;

import gateway.br.com.gateway.application.dto.CreateTipoUsuarioDto;
import gateway.br.com.gateway.domain.model.TipoUsuario;
import gateway.br.com.gateway.infrastructure.persistence.adapter.TipoUsuarioRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class CreateTipoUsuarioUseCase {
    private final TipoUsuarioRepositoryImpl tipoUsuarioRepository;

    public CreateTipoUsuarioUseCase(TipoUsuarioRepositoryImpl tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public TipoUsuario execute(CreateTipoUsuarioDto createTipoUsuarioDto) {
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome(createTipoUsuarioDto.nome());
        tipoUsuario.setDescricao(createTipoUsuarioDto.descricao());
        return this.tipoUsuarioRepository.save(tipoUsuario);
    }
}
