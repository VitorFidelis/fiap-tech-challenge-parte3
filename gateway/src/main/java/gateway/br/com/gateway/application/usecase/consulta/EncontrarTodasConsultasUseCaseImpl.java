package gateway.br.com.gateway.application.usecase.consulta;

import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.application.input.consulta.usecase.EncontrarTodasConsultasUseCase;
import gateway.br.com.gateway.application.mapper.consulta.IConsultaMapper;
import gateway.br.com.gateway.application.output.consulta.ConsultaGateway;
import gateway.br.com.gateway.domain.model.consulta.Consulta;

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
