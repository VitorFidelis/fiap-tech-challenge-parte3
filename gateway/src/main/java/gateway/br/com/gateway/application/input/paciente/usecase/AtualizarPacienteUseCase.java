package gateway.br.com.gateway.application.input.paciente.usecase;

import gateway.br.com.gateway.application.dto.paciente.AtualizaPacienteDTO;
import gateway.br.com.gateway.application.dto.paciente.PacienteResponseDTO;

import java.util.UUID;

public interface AtualizarPacienteUseCase {
    PacienteResponseDTO executar(UUID id, AtualizaPacienteDTO atualizaPacienteDTO);
}
