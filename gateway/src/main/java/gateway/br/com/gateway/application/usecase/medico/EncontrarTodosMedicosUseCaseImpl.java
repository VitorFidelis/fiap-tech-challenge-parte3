package gateway.br.com.gateway.application.usecase.medico;

import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.application.input.medico.usecase.EncontrarTodosMedicosUseCase;
import gateway.br.com.gateway.application.mapper.medico.IMedicoMapper;
import gateway.br.com.gateway.application.output.usuario.medico.MedicoGateway;
import gateway.br.com.gateway.domain.model.usuario.Medico;

import java.util.List;

public class EncontrarTodosMedicosUseCaseImpl implements EncontrarTodosMedicosUseCase {
    private final MedicoGateway medicoGateway;
    private final IMedicoMapper medicoMapper;

    public EncontrarTodosMedicosUseCaseImpl(MedicoGateway medicoGateway, IMedicoMapper medicoMapper) {
        this.medicoGateway = medicoGateway;
        this.medicoMapper = medicoMapper;
    }

    @Override
    public PaginatedResponseDTO<MedicoResponseDTO> executar(
            PaginatedRequestDTO paginacao) {
        PaginatedResult<Medico> medicosPaginados = medicoGateway.listarTodos(paginacao);
        List<MedicoResponseDTO> medicosDTO = medicosPaginados.content()
                .stream()
                .map(medicoMapper::toResponseDTO)
                .toList();
        return new PaginatedResponseDTO<>(
                medicosDTO,
                medicosPaginados.totalItems(),
                medicosPaginados.totalPages(),
                paginacao.page(),
                paginacao.size()
        );

    }
}
