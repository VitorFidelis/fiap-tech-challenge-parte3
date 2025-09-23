package gateway.br.com.gateway.presentation.mapper.usuarios;

import gateway.br.com.gateway.application.dto.usuarios.CreateUsuarioDto;
import gateway.br.com.gateway.application.dto.usuarios.UpdateUsuarioDto;
import gateway.br.com.gateway.domain.model.usuario.Usuario;
import gateway.br.com.gateway.presentation.api.usuarios.UsuarioRequest;
import gateway.br.com.gateway.presentation.api.usuarios.UsuarioResponseDetalhado;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UsuarioApiMapper {
    CreateUsuarioDto fromUsuarioApplicationCreate(UsuarioRequest usuarioRequest);
    UsuarioResponseDetalhado fromUsuarioPresentation(Usuario usuario);
    UpdateUsuarioDto fromUsuarioApplicationUpdate(UsuarioRequest usuarioRequest);
    default Page<UsuarioResponseDetalhado> fromUsuarioPresentatioPage(Page<Usuario> usuarioPage) {
        return usuarioPage.map(this::fromUsuarioPresentation);
    }
}
