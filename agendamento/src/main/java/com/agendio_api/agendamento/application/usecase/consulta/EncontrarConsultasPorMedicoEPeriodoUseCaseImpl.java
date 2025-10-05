package com.agendio_api.agendamento.application.usecase.consulta;

import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaFiltroRequestDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.EncontrarConsultasPorMedicoEPeriodoUseCase;
import com.agendio_api.agendamento.application.port.mapper.consulta.IConsultaMapper;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;
import com.agendio_api.agendamento.domain.model.consulta.FiltroBuscaConsulta;
import com.agendio_api.agendamento.domain.model.valueobject.PeriodoConsultas;

import java.util.List;

public class EncontrarConsultasPorMedicoEPeriodoUseCaseImpl implements EncontrarConsultasPorMedicoEPeriodoUseCase {

    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;

    public EncontrarConsultasPorMedicoEPeriodoUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
    }

    @Override
    public PaginatedResponseDTO<ConsultaResponseDTO> executar(ConsultaFiltroRequestDTO requestDTO, PaginatedRequestDTO paginacao) {
        var periodo = new PeriodoConsultas(requestDTO.inicio(), requestDTO.fim());
        var filtro = new FiltroBuscaConsulta(requestDTO.medicoId(), periodo);

        PaginatedResult<Consulta> consultasPaginadas = consultaGateway.listarPorMedicoEPeriodo(filtro, paginacao);

        List<ConsultaResponseDTO> consultas = consultasPaginadas.content()
                .stream()
                .map(consultaMapper::toResponseDTO)
                .toList();

        return new PaginatedResponseDTO<>(
                consultas,
                consultasPaginadas.totalItems(),
                consultasPaginadas.totalPages(),
                paginacao.page(),
                paginacao.size()
        );
    }

}
