package gateway.br.com.gateway.infrastructure.persistence.mapper;

import gateway.br.com.gateway.domain.model.TipoUsuario;
import gateway.br.com.gateway.infrastructure.persistence.entity.TipoUsuarioEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoUsuarioMapper {
    TipoUsuarioEntity fromTipoUsuarioJpa(TipoUsuario tipoUsuario);
    TipoUsuario fromTipoUsuarioDomain(TipoUsuarioEntity tipoUsuarioEntity);
    List<TipoUsuario> fromTipoUsuarioDomainPage(List<TipoUsuarioEntity> tipoUsuarioEntityList);
}
