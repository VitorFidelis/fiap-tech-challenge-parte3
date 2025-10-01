package gateway.br.com.gateway.application.input.consulta.usecase;

import java.time.LocalDateTime;
import java.util.UUID;

public interface VerificarConsultaDePacienteNoHorarioUseCase {
    /**
     * Verifica se existe uma consulta marcada para um paciente em um determinado horário.
     *
     * @param pacienteID ID do paciente
     * @param dataHora   Data e hora da consulta
     * @return true se existir uma consulta no horário, false caso contrário
     */
    boolean executar(UUID pacienteID, LocalDateTime dataHora);
}
