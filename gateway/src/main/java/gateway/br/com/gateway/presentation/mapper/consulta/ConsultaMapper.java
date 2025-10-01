package gateway.br.com.gateway.presentation.mapper.consulta;

import gateway.br.com.gateway.application.dto.consulta.AgendaConsultaDTO;
import gateway.br.com.gateway.application.dto.consulta.AtualizaConsultaDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaFiltroRequestDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;
import gateway.br.com.gateway.application.mapper.consulta.IConsultaMapper;
import gateway.br.com.gateway.domain.model.consulta.Consulta;
import gateway.br.com.gateway.domain.model.consulta.FiltroBuscaConsulta;
import gateway.br.com.gateway.domain.model.valueobject.PeriodoConsultas;
import gateway.br.com.gateway.infrastructure.datasource.jpa.consulta.JpaConsultaEntity;
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
}

