package gateway.br.com.gateway.application.input.enfermeiro.usecase;

import gateway.br.com.gateway.application.dto.enfermeiro.AtualizaEnfermeiroDTO;
import gateway.br.com.gateway.application.dto.enfermeiro.EnfermeiroResponseDTO;

import java.util.UUID;

public interface AtualizarEnfermeiroUseCase {
    EnfermeiroResponseDTO execute(UUID id, AtualizaEnfermeiroDTO atualizaEnfermeiroDTO);
}
