package gateway.br.com.gateway.application.input.paciente.usecase;

import gateway.br.com.gateway.application.dto.paciente.PacienteResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;

public interface EncontrarTodosPacientesUseCase {

    PaginatedResponseDTO<PacienteResponseDTO> executar(PaginatedRequestDTO paginacao);
}
