package gateway.br.com.gateway.application.usecase.usuarios;

import gateway.br.com.gateway.application.dto.usuarios.UpdateUsuarioDto;
import gateway.br.com.gateway.domain.model.usuario.Usuario;
import gateway.br.com.gateway.infrastructure.persistence.adapter.tipousuarios.TipoUsuarioRepositoryImpl;
import gateway.br.com.gateway.infrastructure.persistence.adapter.usuarios.UsuarioRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateUsuarioUseCase {
    private final UsuarioRepositoryImpl usuarioRepositoryImpl;
    private final TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl;

    public UpdateUsuarioUseCase(
            final UsuarioRepositoryImpl usuarioRepositoryImpl,
            final TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl
    ) {
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;
        this.tipoUsuarioRepositoryImpl = tipoUsuarioRepositoryImpl;
    }

    public Usuario execute(final UUID uuid, final UpdateUsuarioDto updateUsuarioDto) {
        var usuario = this.usuarioRepositoryImpl.findById(uuid);
        usuario.setNome(updateUsuarioDto.nome());
        usuario.setSobrenome(updateUsuarioDto.sobrenome());
        usuario.setEmail(updateUsuarioDto.email());
        usuario.setSenha(updateUsuarioDto.senha());
        var tipoUsuario = this.tipoUsuarioRepositoryImpl.findByNomeIgnoreCase(
                updateUsuarioDto.tipo()
        );
        usuario.setTipoUsuario(tipoUsuario);
        return this.usuarioRepositoryImpl.save(usuario);
    }
}
