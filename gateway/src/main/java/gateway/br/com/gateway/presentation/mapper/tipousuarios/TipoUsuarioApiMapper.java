package gateway.br.com.gateway.presentation.mapper.tipousuarios;

import gateway.br.com.gateway.application.dto.tipousuarios.CreateTipoUsuarioDto;
import gateway.br.com.gateway.application.dto.tipousuarios.UpdateTipoUsuarioDto;
import gateway.br.com.gateway.domain.model.tipousuarios.TipoUsuario;
import gateway.br.com.gateway.presentation.api.tipousuarios.TipoUsuarioRequest;
import gateway.br.com.gateway.presentation.api.tipousuarios.TipoUsuarioResponseDetalhado;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface TipoUsuarioApiMapper {
    CreateTipoUsuarioDto fromDtoApplication(TipoUsuarioRequest tipoUsuarioRequest);
    TipoUsuarioResponseDetalhado fromDtoPresentation(TipoUsuario tipoUsuario);
    UpdateTipoUsuarioDto fromDtoApplicationUpdate(TipoUsuarioRequest tipoUsuarioRequest);
    default Page<TipoUsuarioResponseDetalhado> fromDtoPagePresetation(Page<TipoUsuario> tipoUsuarioPage) {
        return tipoUsuarioPage.map(this::fromDtoPresentation);
    }
}
