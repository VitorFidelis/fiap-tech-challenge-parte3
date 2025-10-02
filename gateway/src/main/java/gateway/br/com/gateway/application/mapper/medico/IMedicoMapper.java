package gateway.br.com.gateway.application.mapper.medico;


import gateway.br.com.gateway.application.dto.medico.AtualizaMedicoDTO;
import gateway.br.com.gateway.application.dto.medico.CadastraMedicoDTO;
import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;
import gateway.br.com.gateway.domain.model.usuario.Medico;
import gateway.br.com.gateway.infrastructure.persistence.entity.usuarios.JpaMedicoEntity;

import java.util.List;

public interface IMedicoMapper {
    List<MedicoResponseDTO> mapList(List<Medico> medicos);

    MedicoResponseDTO toResponseDTO(Medico medico);

    JpaMedicoEntity toJpaMedicoEntity(Medico medico);

    Medico toDomain(CadastraMedicoDTO cadastraMedicoDTO);

    Medico toDomain(AtualizaMedicoDTO atualizaMedicoDTO, Medico medicoExistente);

    Medico toDomainFromJPA(JpaMedicoEntity jpaEntity);
}
