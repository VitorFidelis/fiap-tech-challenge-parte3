package gateway.br.com.gateway.application.input.medico.usecase;

import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;
public interface EncontrarMedicoPorCRMUseCase {
    MedicoResponseDTO executar(String crm);
}
