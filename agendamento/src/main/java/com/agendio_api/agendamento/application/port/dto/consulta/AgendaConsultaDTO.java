package com.agendio_api.agendamento.application.port.dto.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendaConsultaDTO(

        @NotNull(message = "O ID do paciente é obrigatório")
        UUID idPaciente,

        @NotNull(message = "O ID do médico é obrigatório")
        UUID idMedico,

        @NotNull(message = "O ID do enfermeiro agendador é obrigatório")
        UUID idEnfermeiro,

        @NotNull(message = "O horário da consulta é obrigatório")
        @Future(message = "A data da consulta deve ser futura")
        LocalDateTime horarioSolicitado,

        @Size(max = 500, message = "As observações não podem ultrapassar 500 caracteres")
        String observacoes
) {
    public AgendaConsultaDTO {
        // Inicialização padrão do status como AGENDADA
        if (observacoes == null) {
            observacoes = "";
        }
    }
}
