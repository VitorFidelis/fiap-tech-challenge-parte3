package com.agendio_api.agendamento.application.usecase.consulta;

import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.EncontrarTodasConsultasUseCase;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;

import java.util.List;

public class EncontrarTodasConsultasUseCaseImpl implements EncontrarTodasConsultasUseCase {
    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;

    public EncontrarTodasConsultasUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
    }

    @Override
    public PaginatedResponseDTO<ConsultaResponseDTO> executar(PaginatedRequestDTO paginacao) {
        PaginatedResult<Consulta> consultasPaginadas = consultaGateway.listarTodas(paginacao);
        List<ConsultaResponseDTO> consultas = consultasPaginadas.content()
                .stream()
                .map(consultaMapper::toResponseDTO)
                .toList();
        return new PaginatedResponseDTO<>(consultas, consultasPaginadas.totalItems(), consultasPaginadas.totalPages(), paginacao.page(), paginacao.size());
    }
}
