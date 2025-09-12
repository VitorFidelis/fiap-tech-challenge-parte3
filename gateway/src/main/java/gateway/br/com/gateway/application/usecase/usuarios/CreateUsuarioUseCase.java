package gateway.br.com.gateway.application.usecase.usuarios;

import gateway.br.com.gateway.application.dto.usuarios.CreateUsuarioDto;
import gateway.br.com.gateway.domain.model.usuario.Usuario;
import gateway.br.com.gateway.infrastructure.persistence.adapter.tipousuarios.TipoUsuarioRepositoryImpl;
import gateway.br.com.gateway.infrastructure.persistence.adapter.usuarios.UsuarioRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class CreateUsuarioUseCase {
    private final UsuarioRepositoryImpl usuarioRepositoryImpl;
    private final TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl;

    public CreateUsuarioUseCase(
            final UsuarioRepositoryImpl usuarioRepositoryImpl,
            final TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl
    ) {
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;
        this.tipoUsuarioRepositoryImpl = tipoUsuarioRepositoryImpl;
    }

    public Usuario execute(final CreateUsuarioDto createUsuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setNome(createUsuarioDto.nome());
        usuario.setSobrenome(createUsuarioDto.sobrenome());
        usuario.setEmail(createUsuarioDto.email());
        usuario.setSenha(createUsuarioDto.senha());
        var tipoUsuario = this.tipoUsuarioRepositoryImpl.findByNomeIgnoreCase(
                createUsuarioDto.tipo()
        );
        usuario.setTipoUsuario(tipoUsuario);
        return this.usuarioRepositoryImpl.save(usuario);
    }
}
