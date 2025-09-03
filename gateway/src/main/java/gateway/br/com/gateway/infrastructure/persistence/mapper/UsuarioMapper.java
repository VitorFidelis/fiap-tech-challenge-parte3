package gateway.br.com.gateway.infrastructure.persistence.mapper;

import gateway.br.com.gateway.domain.model.Usuario;
import gateway.br.com.gateway.infrastructure.persistence.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface UsuarioMapper {
    UsuarioEntity fromEntityJpa(Usuario usuario);
    Usuario fromEntityDomain(UsuarioEntity usuarioEntity);
    Page<Usuario> fromUsuarioPage(List<UsuarioEntity> usuarioEntityList);
}
