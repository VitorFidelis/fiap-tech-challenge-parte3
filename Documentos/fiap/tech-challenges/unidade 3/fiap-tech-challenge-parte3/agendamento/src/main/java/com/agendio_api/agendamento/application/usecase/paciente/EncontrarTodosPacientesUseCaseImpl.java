package com.agendio_api.agendamento.application.usecase.paciente;

import com.agendio_api.agendamento.application.port.dto.paciente.PacienteResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.input.paciente.usecase.EncontrarTodosPacientesUseCase;
import com.agendio_api.agendamento.application.port.mapper.paciente.IPacienteMapper;
import com.agendio_api.agendamento.application.port.output.usuario.paciente.PacienteGateway;
import com.agendio_api.agendamento.domain.model.usuario.Paciente;

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
