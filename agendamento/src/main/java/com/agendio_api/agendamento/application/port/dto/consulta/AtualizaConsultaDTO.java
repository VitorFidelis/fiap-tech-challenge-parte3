package com.agendio_api.agendamento.application.port.dto.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AtualizaConsultaDTO(

        @Future(message = "A data da consulta deve ser futura")
        LocalDateTime horarioSolicitado,

        @Size(max = 500, message = "As observações não podem ultrapassar 500 caracteres")
        String observacoes
) {
}
