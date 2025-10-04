package com.agendio_api.agendamento.application.port.mapper.enfermeiro;

import com.agendio_api.agendamento.application.port.dto.enfermeiro.AtualizaEnfermeiroDTO;
import com.agendio_api.agendamento.application.port.dto.enfermeiro.CadastraEnfermeiroDTO;
import com.agendio_api.agendamento.application.port.dto.enfermeiro.EnfermeiroResponseDTO;
import com.agendio_api.agendamento.domain.model.usuario.Enfermeiro;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.enfermeiro.JpaEnfermeiroEntity;

import java.util.List;

public interface IEnfermeiroMapper {
    List<EnfermeiroResponseDTO> mapList(List<Enfermeiro> enfermeiros);

    EnfermeiroResponseDTO toResponseDTO(Enfermeiro enfermeiro);

    JpaEnfermeiroEntity toJpaEnfermeiroEntity(Enfermeiro enfermeiro);

    Enfermeiro toDomain(CadastraEnfermeiroDTO cadastraEnfermeiroDTO);

    Enfermeiro toDomain(AtualizaEnfermeiroDTO atualizaEnfermeiroDTO, Enfermeiro enfermeiroExistente);

    Enfermeiro toDomain(JpaEnfermeiroEntity jpaEntity);

}
