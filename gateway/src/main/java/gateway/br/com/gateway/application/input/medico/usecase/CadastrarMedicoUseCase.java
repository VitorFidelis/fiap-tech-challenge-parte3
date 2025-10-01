package gateway.br.com.gateway.application.input.medico.usecase;

import gateway.br.com.gateway.application.dto.medico.CadastraMedicoDTO;
import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;

public interface CadastrarMedicoUseCase {
    MedicoResponseDTO executar(CadastraMedicoDTO cadastraMedicoDTO);
}
