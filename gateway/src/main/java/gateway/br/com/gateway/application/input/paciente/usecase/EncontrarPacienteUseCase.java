package gateway.br.com.gateway.application.input.paciente.usecase;

import gateway.br.com.gateway.application.dto.paciente.PacienteResponseDTO;

import java.util.UUID;

public interface EncontrarPacienteUseCase {
    PacienteResponseDTO executar(UUID id);
}
