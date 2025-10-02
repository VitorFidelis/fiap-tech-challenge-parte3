package gateway.br.com.gateway.application.mapper.enfermeiro;

import gateway.br.com.gateway.application.dto.enfermeiro.AtualizaEnfermeiroDTO;
import gateway.br.com.gateway.application.dto.enfermeiro.CadastraEnfermeiroDTO;
import gateway.br.com.gateway.application.dto.enfermeiro.EnfermeiroResponseDTO;
import gateway.br.com.gateway.domain.model.usuario.Enfermeiro;
import gateway.br.com.gateway.infrastructure.persistence.entity.usuarios.JpaEnfermeiroEntity;

import java.util.List;

public interface IEnfermeiroMapper {
    List<EnfermeiroResponseDTO> mapList(List<Enfermeiro> enfermeiros);

    EnfermeiroResponseDTO toResponseDTO(Enfermeiro enfermeiro);

    JpaEnfermeiroEntity toJpaEnfermeiroEntity(Enfermeiro enfermeiro);

    Enfermeiro toDomain(CadastraEnfermeiroDTO cadastraEnfermeiroDTO);

    Enfermeiro toDomain(AtualizaEnfermeiroDTO atualizaEnfermeiroDTO, Enfermeiro enfermeiroExistente);

    Enfermeiro toDomain(JpaEnfermeiroEntity jpaEntity);

}
