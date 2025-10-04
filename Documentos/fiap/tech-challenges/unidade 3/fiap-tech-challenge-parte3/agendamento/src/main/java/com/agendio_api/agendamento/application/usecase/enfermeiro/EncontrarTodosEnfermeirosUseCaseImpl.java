package com.agendio_api.agendamento.application.usecase.enfermeiro;

import com.agendio_api.agendamento.application.port.dto.enfermeiro.EnfermeiroResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.input.enfermeiro.usecase.EncontrarTodosEnfermeirosUseCase;
import com.agendio_api.agendamento.application.port.mapper.enfermeiro.IEnfermeiroMapper;
import com.agendio_api.agendamento.application.port.output.usuario.enfermeiro.EnfermeiroGateway;
import com.agendio_api.agendamento.domain.model.usuario.Enfermeiro;

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
