package gateway.br.com.gateway.application.input.medico.usecase;

import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;
import java.util.UUID;

public interface EncontrarMedicoPorIdUseCase {
    MedicoResponseDTO executar(UUID id);
}
