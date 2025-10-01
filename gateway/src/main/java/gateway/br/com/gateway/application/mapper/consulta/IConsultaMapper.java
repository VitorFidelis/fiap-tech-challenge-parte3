package gateway.br.com.gateway.application.mapper.consulta;

import gateway.br.com.gateway.application.dto.consulta.AgendaConsultaDTO;
import gateway.br.com.gateway.application.dto.consulta.AtualizaConsultaDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaFiltroRequestDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;
import gateway.br.com.gateway.domain.model.consulta.Consulta;
import gateway.br.com.gateway.domain.model.consulta.FiltroBuscaConsulta;
import gateway.br.com.gateway.infrastructure.datasource.jpa.consulta.JpaConsultaEntity;

import java.util.List;


public interface IConsultaMapper {
    List<ConsultaResponseDTO> mapList(List<Consulta> consultas);

    ConsultaResponseDTO toResponseDTO(Consulta consulta);

    JpaConsultaEntity toJpaConsultaEntity(Consulta consulta);

    Consulta toDomain(AgendaConsultaDTO agendaConsultaDTO);

    Consulta toDomain(AtualizaConsultaDTO atualizaConsultaDTO, Consulta consultaExistente);

    Consulta toDomain(JpaConsultaEntity jpaEntity);

    FiltroBuscaConsulta toFiltroConsulta(ConsultaFiltroRequestDTO dto);
}
