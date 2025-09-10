package gateway.br.com.gateway.infrastructure.persistence.mapper.usuarios;

import gateway.br.com.gateway.domain.model.usuario.Usuario;
import gateway.br.com.gateway.infrastructure.persistence.entity.usuarios.UsuarioEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioEntity fromEntityJpa(Usuario usuario);
    Usuario fromEntityDomain(UsuarioEntity usuarioEntity);
    default Page<Usuario> fromUsuarioPage(Page<UsuarioEntity> usuarioEntityPage) {
        return usuarioEntityPage.map(this::fromEntityDomain);
    }
}
