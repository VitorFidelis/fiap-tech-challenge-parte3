package gateway.br.com.gateway.application.usecase.enfermeiro;

import gateway.br.com.gateway.application.dto.enfermeiro.EnfermeiroResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.application.input.enfermeiro.usecase.EncontrarTodosEnfermeirosUseCase;
import gateway.br.com.gateway.application.mapper.enfermeiro.IEnfermeiroMapper;
import gateway.br.com.gateway.application.output.usuario.enfermeiro.EnfermeiroGateway;
import gateway.br.com.gateway.domain.model.usuario.Enfermeiro;

import java.util.List;

public class EncontrarTodosEnfermeirosUseCaseImpl implements EncontrarTodosEnfermeirosUseCase {

    private final EnfermeiroGateway enfermeiroGateway;
    private final IEnfermeiroMapper enfermeiroMapper;

    public EncontrarTodosEnfermeirosUseCaseImpl(EnfermeiroGateway enfermeiroGateway, IEnfermeiroMapper enfermeiroMapper) {
        this.enfermeiroGateway = enfermeiroGateway;
        this.enfermeiroMapper = enfermeiroMapper;
    }

    @Override
    public PaginatedResponseDTO<EnfermeiroResponseDTO> executar(
            PaginatedRequestDTO paginacao) {

        PaginatedResult<Enfermeiro> enfermeirosPaginados = enfermeiroGateway.listarTodos(paginacao);
        List<EnfermeiroResponseDTO> enfermeirosDto = enfermeirosPaginados.content()
                .stream()
                .map(enfermeiroMapper::toResponseDTO)
                .toList();

        return new PaginatedResponseDTO<>(
                enfermeirosDto,
                enfermeirosPaginados.totalItems(),
                enfermeirosPaginados.totalPages(),
                paginacao.page(),
                paginacao.size()
        );


    }
}
