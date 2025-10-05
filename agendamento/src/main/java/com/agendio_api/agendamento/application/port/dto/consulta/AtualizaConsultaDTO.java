package com.agendio_api.agendamento.application.port.dto.consulta;

import com.agendio_api.agendamento.domain.model.consulta.StatusConsulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AtualizaConsultaDTO(

        @Future(message = "A data da consulta deve ser futura")
        LocalDateTime horarioSolicitado,

        @NotNull
        StatusConsulta status,

        @Size(max = 500, message = "As observações não podem ultrapassar 500 caracteres")
        String observacoes
) {
}
