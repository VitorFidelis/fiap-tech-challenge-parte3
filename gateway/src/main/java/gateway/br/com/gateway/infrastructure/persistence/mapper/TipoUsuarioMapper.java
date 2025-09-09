package gateway.br.com.gateway.infrastructure.persistence.mapper;

import gateway.br.com.gateway.domain.model.TipoUsuario;
import gateway.br.com.gateway.infrastructure.persistence.entity.TipoUsuarioEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface TipoUsuarioMapper {
    TipoUsuarioEntity fromTipoUsuarioJpa(TipoUsuario tipoUsuario);
    TipoUsuario fromTipoUsuarioDomain(TipoUsuarioEntity tipoUsuarioEntity);
    default Page<TipoUsuario> fromTipoUsuarioDomainList(Page<TipoUsuarioEntity> tipoUsuarioEntityList) {
        return tipoUsuarioEntityList.map(this::fromTipoUsuarioDomain);
    }
}
