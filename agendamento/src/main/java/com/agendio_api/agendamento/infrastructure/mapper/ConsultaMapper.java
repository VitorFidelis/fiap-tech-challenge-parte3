package com.agendio_api.agendamento.infrastructure.mapper;

import com.agendio_api.agendamento.application.port.dto.consulta.AgendaConsultaDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.AtualizaConsultaDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaFiltroRequestDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.AtualizarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ConsultaResponseGraphqlDTO;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;
import com.agendio_api.agendamento.domain.model.consulta.FiltroBuscaConsulta;
import com.agendio_api.agendamento.domain.model.valueobject.PeriodoConsultas;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.consulta.JpaConsultaEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class ConsultaMapper implements IConsultaMapper {

    @Override
    public List<ConsultaResponseDTO> mapList(List<Consulta> consultas) {
        if (consultas == null || consultas.isEmpty()) {
            return Collections.emptyList();
        }
        return consultas.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultaResponseDTO toResponseDTO(Consulta consulta) {
        if (consulta == null) {
            return null;
        }
        return new ConsultaResponseDTO(
                consulta.getId(),
                consulta.getMedicoId(),
                consulta.getPacienteId(),
                consulta.getEnfermeiroId(),
                consulta.getHorarioSolicitado(),
                consulta.getStatus(),
                consulta.getObservacoes(),
                consulta.getCriadoEm(),
                consulta.getAtualizadoEm());
    }

    @Override
    public Consulta toDomain(AgendaConsultaDTO agendaConsultaDTO) {
        return Consulta.agendar(
                agendaConsultaDTO.idMedico(),
                agendaConsultaDTO.idEnfermeiro(),
                agendaConsultaDTO.idPaciente(),
                agendaConsultaDTO.horarioSolicitado(),
                agendaConsultaDTO.observacoes());
    }

    @Override
    public JpaConsultaEntity toJpaConsultaEntity(Consulta consulta) {
        JpaConsultaEntity jpaConsultaEntity = new JpaConsultaEntity();
        jpaConsultaEntity.setId(consulta.getId());
        jpaConsultaEntity.setMedicoId(consulta.getMedicoId());
        jpaConsultaEntity.setPacienteId(consulta.getPacienteId());
        jpaConsultaEntity.setEnfermeiroId(consulta.getEnfermeiroId());
        jpaConsultaEntity.setHorarioSolicitado(consulta.getHorarioSolicitado());
        jpaConsultaEntity.setStatus(consulta.getStatus());
        jpaConsultaEntity.setObservacoes(consulta.getObservacoes());
        jpaConsultaEntity.setCriadoEm(consulta.getCriadoEm());
        jpaConsultaEntity.setAtualizadoEm(consulta.getAtualizadoEm());
        jpaConsultaEntity.setAtivo(consulta.isAtivo());
        return jpaConsultaEntity;
    }

    @Override
    public Consulta toDomain(AtualizaConsultaDTO dto, Consulta consultaExistente) {
        consultaExistente.setHorarioSolicitado(dto.horarioSolicitado());
        consultaExistente.setStatus(dto.status());
        consultaExistente.setObservacoes(dto.observacoes());
        return consultaExistente;
    }

    @Override
    public Consulta toDomain(JpaConsultaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }

        return Consulta.reconstituir(
                jpaEntity.getId(),
                jpaEntity.getMedicoId(),
                jpaEntity.getEnfermeiroId(),
                jpaEntity.getPacienteId(),
                jpaEntity.getHorarioSolicitado(),
                jpaEntity.getObservacoes(),
                jpaEntity.getStatus()
        );
    }

    @Override
    public FiltroBuscaConsulta toFiltroConsulta(ConsultaFiltroRequestDTO dto) {
        var periodo = new PeriodoConsultas(dto.inicio(), dto.fim());
        return new FiltroBuscaConsulta(dto.medicoId(), periodo);
    }

    @Override
    public Consulta toDomain(AtualizarConsultaGraphqlDTO dto, Consulta consultaExistente) {
        if (dto == null){
            return null;
        }

        if (dto.horarioSolicitado() != null) {
            consultaExistente.setHorarioSolicitado(dto.horarioSolicitado());
        }

        if (dto.observacoes() != null) {
            consultaExistente.setObservacoes(dto.observacoes());
        }

        if (dto.status() != null){
            consultaExistente.setStatus(dto.status());
        }

        return consultaExistente;
    }
    @Override
    public ConsultaResponseGraphqlDTO toConsultaGraphqlDTO(Consulta consulta) {
        if (consulta == null){
            return null;
        }

        return new ConsultaResponseGraphqlDTO(
                consulta.getId(),
                consulta.getMedicoId(),
                consulta.getPacienteId(),
                consulta.getEnfermeiroId(),
                consulta.getHorarioSolicitado(),
                consulta.getStatus(),
                consulta.getObservacoes(),
                consulta.getCriadoEm(),
                consulta.getAtualizadoEm()
        );
    }
}


