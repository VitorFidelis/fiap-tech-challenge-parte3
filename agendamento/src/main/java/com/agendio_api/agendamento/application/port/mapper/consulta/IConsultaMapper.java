package com.agendio_api.agendamento.application.port.mapper.consulta;

import com.agendio_api.agendamento.application.port.dto.consulta.AgendaConsultaDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.AtualizaConsultaDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaFiltroRequestDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.AtualizarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ConsultaResponseGraphqlDTO;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;
import com.agendio_api.agendamento.domain.model.consulta.FiltroBuscaConsulta;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.consulta.JpaConsultaEntity;

import java.util.List;

public interface IConsultaMapper {
    List<ConsultaResponseDTO> mapList(List<Consulta> consultas);

    ConsultaResponseDTO toResponseDTO(Consulta consulta);

    JpaConsultaEntity toJpaConsultaEntity(Consulta consulta);

    Consulta toDomain(AgendaConsultaDTO agendaConsultaDTO);

    Consulta toDomain(AtualizaConsultaDTO atualizaConsultaDTO, Consulta consultaExistente);

    Consulta toDomain(JpaConsultaEntity jpaEntity);

    Consulta toDomain(AtualizarConsultaGraphqlDTO dto, Consulta consultaExistente);

    FiltroBuscaConsulta toFiltroConsulta(ConsultaFiltroRequestDTO dto);

    ConsultaResponseGraphqlDTO toConsultaGraphqlDTO(Consulta consulta);
}
