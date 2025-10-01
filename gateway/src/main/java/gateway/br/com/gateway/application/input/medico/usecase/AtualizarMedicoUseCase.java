package gateway.br.com.gateway.application.input.medico.usecase;

import gateway.br.com.gateway.application.dto.medico.AtualizaMedicoDTO;
import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;

import java.util.UUID;

public interface AtualizarMedicoUseCase {
    MedicoResponseDTO executar(UUID id, AtualizaMedicoDTO atualizaMedicoDTO);
}
