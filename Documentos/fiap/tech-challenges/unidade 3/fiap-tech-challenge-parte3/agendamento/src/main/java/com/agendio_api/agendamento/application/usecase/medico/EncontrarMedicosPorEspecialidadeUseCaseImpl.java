package com.agendio_api.agendamento.application.usecase.medico;

import com.agendio_api.agendamento.application.port.dto.medico.EncontraMedicosPorEspecialidadeRequestDTO;
import com.agendio_api.agendamento.application.port.dto.medico.MedicoResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.input.medico.usecase.EncontrarMedicosPorEspecialidadeUseCase;
import com.agendio_api.agendamento.application.port.mapper.medico.IMedicoMapper;
import com.agendio_api.agendamento.application.port.output.usuario.medico.MedicoGateway;
import com.agendio_api.agendamento.domain.model.usuario.Medico;

import java.util.List;

public class EncontrarMedicosPorEspecialidadeUseCaseImpl implements EncontrarMedicosPorEspecialidadeUseCase {
    private final MedicoGateway medicoGateway;
    private final IMedicoMapper medicoMapper;

    public EncontrarMedicosPorEspecialidadeUseCaseImpl(MedicoGateway medicoGateway, IMedicoMapper medicoMapper) {
        this.medicoGateway = medicoGateway;
        this.medicoMapper = medicoMapper;
    }

    @Override
    public PaginatedResponseDTO<MedicoResponseDTO> executar(EncontraMedicosPorEspecialidadeRequestDTO request) {
        PaginatedResult<Medico> medicosPaginados = medicoGateway.buscarPorEspecialidade(request.especialidade(), request.paginacao());
        List<MedicoResponseDTO> medicosDTO = medicosPaginados.content()
                .stream()
                .map(medicoMapper::toResponseDTO)
                .toList();
        return new PaginatedResponseDTO<>(
                medicosDTO,
                medicosPaginados.totalItems(),
                medicosPaginados.totalPages(),
                request.paginacao().page(),
                request.paginacao().size()
        );

    }
}
