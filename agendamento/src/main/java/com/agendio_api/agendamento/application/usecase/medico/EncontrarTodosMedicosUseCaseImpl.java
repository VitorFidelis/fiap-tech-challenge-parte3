package com.agendio_api.agendamento.application.usecase.medico;

import com.agendio_api.agendamento.application.port.dto.medico.MedicoResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.input.medico.usecase.EncontrarTodosMedicosUseCase;
import com.agendio_api.agendamento.application.port.mapper.medico.IMedicoMapper;
import com.agendio_api.agendamento.application.port.output.usuario.medico.MedicoGateway;
import com.agendio_api.agendamento.domain.model.usuario.Medico;

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
