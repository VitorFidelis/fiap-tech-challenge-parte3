package com.agendio_api.agendamento.application.usecase.consulta;

import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.dto.usuario.UsuarioIdFiltroPaginadoRequestDTO;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.EncontrarConsultasPorMedicoUseCase;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;

import java.util.List;

public class EncontrarConsultasPorMedicoUseCaseImpl implements EncontrarConsultasPorMedicoUseCase {
    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;

    public EncontrarConsultasPorMedicoUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
    }

    @Override
    public PaginatedResponseDTO<ConsultaResponseDTO> executar(UsuarioIdFiltroPaginadoRequestDTO dto) {
        PaginatedResult<Consulta> consultasPaginadas = consultaGateway.listarPorMedico(dto);
        List<ConsultaResponseDTO> consultas = consultasPaginadas.content()
                .stream()
                .map(consultaMapper::toResponseDTO)
                .toList();
        return new PaginatedResponseDTO<>(consultas, consultasPaginadas.totalItems(), consultasPaginadas.totalPages(), dto.paginacao().page(), dto.paginacao().size());
    }
}
