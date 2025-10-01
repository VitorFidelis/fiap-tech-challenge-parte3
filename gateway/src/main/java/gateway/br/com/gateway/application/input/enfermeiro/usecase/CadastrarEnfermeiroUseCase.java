package gateway.br.com.gateway.application.input.enfermeiro.usecase;

import gateway.br.com.gateway.application.dto.enfermeiro.CadastraEnfermeiroDTO;
import gateway.br.com.gateway.application.dto.enfermeiro.EnfermeiroResponseDTO;

public interface CadastrarEnfermeiroUseCase {
    EnfermeiroResponseDTO execute(CadastraEnfermeiroDTO cadastraEnfermeiroDTO);
}
