package gateway.br.com.gateway.application.input.consulta.usecase;

import java.time.LocalDateTime;
import java.util.UUID;

public interface VerificarConsultaDeMedicoNoHorarioUseCase {
    /**
     * Verifica se existe uma consulta marcada para um médico em um determinado horário.
     *
     * @param medicoId ID do médico
     * @param dataHora Data e hora da consulta
     * @return true se existir uma consulta no horário, false caso contrário
     */
    boolean executar(UUID medicoId, LocalDateTime dataHora);
}
