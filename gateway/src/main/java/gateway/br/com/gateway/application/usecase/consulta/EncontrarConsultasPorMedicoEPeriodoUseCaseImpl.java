package gateway.br.com.gateway.application.usecase.consulta;

import gateway.br.com.gateway.application.dto.consulta.ConsultaFiltroRequestDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.application.input.consulta.usecase.EncontrarConsultasPorMedicoEPeriodoUseCase;
import gateway.br.com.gateway.application.mapper.consulta.IConsultaMapper;
import gateway.br.com.gateway.application.output.consulta.ConsultaGateway;
import gateway.br.com.gateway.domain.model.consulta.Consulta;
import gateway.br.com.gateway.domain.model.consulta.FiltroBuscaConsulta;
import gateway.br.com.gateway.domain.model.valueobject.PeriodoConsultas;

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
