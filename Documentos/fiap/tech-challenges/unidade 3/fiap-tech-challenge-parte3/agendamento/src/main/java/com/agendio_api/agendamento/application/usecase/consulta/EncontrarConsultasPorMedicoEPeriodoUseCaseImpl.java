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
import com.agendio_api.agendamento.domain.model.consulta.FiltroConsulta;
import com.agendio_api.agendamento.domain.model.valueobject.PeriodoConsulta;

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
        // Cria os Value Objects
        var periodo = new PeriodoConsulta(requestDTO.inicio(), requestDTO.fim());
        var filtro = new FiltroConsulta(requestDTO.medicoId(), periodo);

        // Usa o gateway com o filtro de domínio (não o DTO)
        PaginatedResult<Consulta> consultasPaginadas = consultaGateway.listarPorMedicoEPeriodo(filtro, paginacao);

        // Mapeia resultado para DTO de saída
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
