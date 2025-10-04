package com.agendio_api.agendamento.infrastructure.datasource.jpa.consulta;

import com.agendio_api.agendamento.domain.model.consulta.StatusConsulta;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface JpaConsultaRepositoryCustom {
    List<JpaConsultaEntity> findByFilter(UUID pacienteId, UUID medicoId, UUID enfermeiroId,
                                         StatusConsulta statusConsulta, LocalDateTime dataMin,
                                         LocalDateTime dataMax);
}
