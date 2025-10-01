package gateway.br.com.gateway.application.input.enfermeiro.usecase;

import gateway.br.com.gateway.application.dto.enfermeiro.EnfermeiroResponseDTO;

import java.util.UUID;

public interface EncontrarEnfermeiroUseCase {
    EnfermeiroResponseDTO execute(UUID id);
}
