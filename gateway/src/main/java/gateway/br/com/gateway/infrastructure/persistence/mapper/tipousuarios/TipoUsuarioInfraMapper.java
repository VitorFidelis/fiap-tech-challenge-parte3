package gateway.br.com.gateway.infrastructure.persistence.mapper.tipousuarios;

import gateway.br.com.gateway.domain.model.tipousuarios.TipoUsuario;
import gateway.br.com.gateway.infrastructure.persistence.entity.tipousuarios.TipoUsuarioEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface TipoUsuarioInfraMapper {
    TipoUsuarioEntity fromTipoUsuarioJpa(TipoUsuario tipoUsuario);
    TipoUsuario fromTipoUsuarioDomain(TipoUsuarioEntity tipoUsuarioEntity);
    default Page<TipoUsuario> fromTipoUsuarioDomainPage(Page<TipoUsuarioEntity> tipoUsuarioEntityList) {
        return tipoUsuarioEntityList.map(this::fromTipoUsuarioDomain);
    }
}
