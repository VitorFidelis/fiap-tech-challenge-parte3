package gateway.br.com.gateway.presentation.mapper;

import gateway.br.com.gateway.application.dto.CreateTipoUsuarioDto;
import gateway.br.com.gateway.application.dto.UpdateTipoUsuarioDto;
import gateway.br.com.gateway.domain.model.TipoUsuario;
import gateway.br.com.gateway.domain.model.TipoUsuarioEnum;
import gateway.br.com.gateway.presentation.api.TipoUsuarioRequest;
import gateway.br.com.gateway.presentation.api.TipoUsuarioResponseDetalhado;

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
    // Métodos auxiliares de conversão usados pelo MapStruct
    default TipoUsuarioEnum map(String value) {
        return TipoUsuarioEnum.fromString(value);
    }
    default String map(TipoUsuarioEnum value) {
        return value != null ? value.toValue() : null;
    }


}
