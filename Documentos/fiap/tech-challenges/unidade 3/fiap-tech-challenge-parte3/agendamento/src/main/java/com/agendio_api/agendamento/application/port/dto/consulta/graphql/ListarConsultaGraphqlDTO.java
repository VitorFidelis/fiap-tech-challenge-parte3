package com.agendio_api.agendamento.application.port.dto.consulta.graphql;

import com.agendio_api.agendamento.domain.model.consulta.StatusConsulta;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.time.LocalDateTime;
import java.util.UUID;

@SchemaMapping("ListarConsultaInput")
public class ListarConsultaGraphqlDTO{
    private UUID pacienteId;
    private UUID medicoId;
    private UUID enfermeiroId;
    private StatusConsulta statusConsulta;
    private LocalDateTime dataMin;
    private LocalDateTime dataMax;

    public ListarConsultaGraphqlDTO(){}

    public ListarConsultaGraphqlDTO(UUID pacienteId, UUID medicoId, UUID enfermeiroId, StatusConsulta statusConsulta, LocalDateTime dataMin, LocalDateTime dataMax) {
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.enfermeiroId = enfermeiroId;
        this.statusConsulta = statusConsulta;
        this.dataMin = dataMin;
        this.dataMax = dataMax;
    }

    public UUID getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(UUID pacienteId) {
        this.pacienteId = pacienteId;
    }

    public UUID getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(UUID medicoId) {
        this.medicoId = medicoId;
    }

    public UUID getEnfermeiroId() {
        return enfermeiroId;
    }

    public void setEnfermeiroId(UUID enfermeiroId) {
        this.enfermeiroId = enfermeiroId;
    }

    public StatusConsulta getStatusConsulta() {
        return statusConsulta;
    }

    public void setStatusConsulta(StatusConsulta statusConsulta) {
        this.statusConsulta = statusConsulta;
    }

    public LocalDateTime getDataMin() {
        return dataMin;
    }

    public void setDataMin(LocalDateTime dataMin) {
        this.dataMin = dataMin;
    }

    public LocalDateTime getDataMax() {
        return dataMax;
    }

    public void setDataMax(LocalDateTime dataMax) {
        this.dataMax = dataMax;
    }
}

