package gateway.br.com.gateway.application.usecase.paciente;

import gateway.br.com.gateway.application.dto.paciente.PacienteResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.application.input.paciente.usecase.EncontrarTodosPacientesUseCase;
import gateway.br.com.gateway.application.mapper.paciente.IPacienteMapper;
import gateway.br.com.gateway.application.output.usuario.paciente.PacienteGateway;
import gateway.br.com.gateway.domain.model.usuario.Paciente;

import java.util.List;

public class EncontrarTodosPacientesUseCaseImpl implements EncontrarTodosPacientesUseCase {
    private final PacienteGateway pacienteGateway;
    private final IPacienteMapper pacienteMapper;

    public EncontrarTodosPacientesUseCaseImpl(PacienteGateway pacienteGateway, IPacienteMapper pacienteMapper) {
        this.pacienteGateway = pacienteGateway;
        this.pacienteMapper = pacienteMapper;
    }

    @Override
    public PaginatedResponseDTO<PacienteResponseDTO> executar(PaginatedRequestDTO paginacao) {
        PaginatedResult<Paciente> pacientesPaginados = pacienteGateway.listarTodos(paginacao);
        List<PacienteResponseDTO> pacientes = pacientesPaginados.content()
                .stream()
                .map(pacienteMapper::toResponseDTO)
                .toList();
        return new PaginatedResponseDTO<>(pacientes, pacientesPaginados.totalItems(), pacientesPaginados.totalPages(), paginacao.page(), paginacao.size());
    }
}
