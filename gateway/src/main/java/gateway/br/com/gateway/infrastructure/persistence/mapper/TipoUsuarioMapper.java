package gateway.br.com.gateway.infrastructure.persistence.mapper;

import gateway.br.com.gateway.domain.model.TipoUsuario;
import gateway.br.com.gateway.infrastructure.persistence.entity.TipoUsuarioEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface TipoUsuarioMapper {
    TipoUsuarioEntity fromTipoUsuarioJpa(TipoUsuario tipoUsuario);
    TipoUsuario fromTipoUsuarioDomain(TipoUsuarioEntity tipoUsuarioEntity);
    Page<TipoUsuario> fromTipoUsuarioDomainPage(List<TipoUsuarioEntity> tipoUsuarioEntityList);
}
