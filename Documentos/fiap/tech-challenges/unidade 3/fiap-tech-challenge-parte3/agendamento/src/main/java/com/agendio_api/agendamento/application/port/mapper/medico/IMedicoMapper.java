package com.agendio_api.agendamento.application.port.mapper.medico;

import com.agendio_api.agendamento.application.port.dto.medico.AtualizaMedicoDTO;
import com.agendio_api.agendamento.application.port.dto.medico.CadastraMedicoDTO;
import com.agendio_api.agendamento.application.port.dto.medico.MedicoResponseDTO;
import com.agendio_api.agendamento.domain.model.usuario.Medico;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.medico.JpaMedicoEntity;

import java.util.List;

public interface IMedicoMapper {
    List<MedicoResponseDTO> mapList(List<Medico> medicos);

    MedicoResponseDTO toResponseDTO(Medico medico);

    JpaMedicoEntity toJpaMedicoEntity(Medico medico);

    Medico toDomain(CadastraMedicoDTO cadastraMedicoDTO);

    Medico toDomain(AtualizaMedicoDTO atualizaMedicoDTO, Medico medicoExistente);

    Medico toDomainFromJPA(JpaMedicoEntity jpaEntity);
}
