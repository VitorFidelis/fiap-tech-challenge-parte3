package com.agendio_api.agendamento.domain.model.consulta;

import com.agendio_api.agendamento.domain.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
public class Consulta extends BaseEntity {

    private final UUID medicoId;
    private final UUID enfermeiroId;
    private final UUID pacienteId;
    private LocalDateTime horarioSolicitado;
    private StatusConsulta status;
    private String observacoes;


    private Consulta(
            UUID id,
            UUID medicoId,
            UUID enfermeiroId,
            UUID pacienteId,
            LocalDateTime horarioSolicitado,
            String observacoes,
            StatusConsulta status) {

        super();

        this.medicoId = Objects.requireNonNull(medicoId);
        this.enfermeiroId = Objects.requireNonNull(enfermeiroId);
        this.pacienteId = Objects.requireNonNull(pacienteId);
        this.horarioSolicitado = Objects.requireNonNull(horarioSolicitado);
        this.observacoes = observacoes;
        this.status = Objects.requireNonNull(status);
    }

    private Consulta(
            UUID medicoId,
            UUID enfermeiroId,
            UUID pacienteId,
            LocalDateTime horarioSolicitado,
            String observacoes,
            StatusConsulta status) {

        super();

        this.medicoId = Objects.requireNonNull(medicoId);
        this.enfermeiroId = Objects.requireNonNull(enfermeiroId);
        this.pacienteId = Objects.requireNonNull(pacienteId);
        this.horarioSolicitado = Objects.requireNonNull(horarioSolicitado);
        this.observacoes = observacoes;
        this.status = Objects.requireNonNull(status);
    }

    public static Consulta agendar(UUID medicoId,
                                   UUID enfermeiroId,
                                   UUID pacienteId,
                                   LocalDateTime horarioSolicitado,
                                   String observacoes) {
        return new Consulta(
                medicoId,
                enfermeiroId,
                pacienteId,
                horarioSolicitado,
                observacoes,
                StatusConsulta.AGENDADA
        );
    }

    //reconstituir do banco
    public static Consulta reconstituir(
            UUID id,
            UUID medicoId,
            UUID enfermeiroId,
            UUID pacienteId,
            LocalDateTime horarioSolicitado,
            String observacoes,
            StatusConsulta status) {
        return new Consulta(
                id,
                medicoId,
                enfermeiroId,
                pacienteId,
                horarioSolicitado,
                observacoes,
                status
        );
    }


    public void atualizarObservacoes(String novasObservacoes) {
        this.observacoes = novasObservacoes;
        touch();
    }

    public void cancelar(String motivoCancelamento) {
        if (this.status == StatusConsulta.AGENDADA) {
            this.status = StatusConsulta.CANCELADA;
            this.setObservacoes("Consulta cancelada: " + motivoCancelamento);
            this.desativar();
        }
    }

    public void concluir() {
        if (this.horarioSolicitado.isAfter(LocalDateTime.now())) {
            this.status = StatusConsulta.REALIZADA;
            touch();
        }
    }

}

