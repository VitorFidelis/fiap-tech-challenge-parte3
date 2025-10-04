package com.agendio_api.agendamento.application.usecase.consulta;

import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.dto.usuario.UsuarioIdFiltroPaginadoRequestDTO;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.EncontrarConsultasPorPacienteUseCase;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;

import java.util.List;

public class EncontrarConsultasPorPacienteUseCaseImpl implements EncontrarConsultasPorPacienteUseCase {
    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;

    public EncontrarConsultasPorPacienteUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
    }

    @Override
    public PaginatedResponseDTO<ConsultaResponseDTO> executar(UsuarioIdFiltroPaginadoRequestDTO request) {
        PaginatedResult<Consulta> consultasPaginadas = consultaGateway.listarPorPaciente(request);
        List<ConsultaResponseDTO> consultas = consultasPaginadas.content()
                .stream()
                .map(consultaMapper::toResponseDTO)
                .toList();
        return new PaginatedResponseDTO<>(consultas, consultasPaginadas.totalItems(), consultasPaginadas.totalPages(),
                request.paginacao().page(), request.paginacao().size());
    }
}
