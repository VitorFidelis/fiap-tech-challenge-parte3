package gateway.br.com.gateway.application.input.medico.usecase;

import gateway.br.com.gateway.application.dto.medico.EncontraMedicosPorEspecialidadeRequestDTO;
import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;

public interface EncontrarMedicosPorEspecialidadeUseCase {
    PaginatedResponseDTO<MedicoResponseDTO> executar(EncontraMedicosPorEspecialidadeRequestDTO request);
}
