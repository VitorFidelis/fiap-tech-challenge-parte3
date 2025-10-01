package gateway.br.com.gateway.application.usecase.consulta;

import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.application.dto.usuarios.UsuarioIdFiltroPaginadoRequestDTO;
import gateway.br.com.gateway.application.input.consulta.usecase.EncontrarConsultasPorPacienteUseCase;
import gateway.br.com.gateway.application.mapper.consulta.IConsultaMapper;
import gateway.br.com.gateway.application.output.consulta.ConsultaGateway;
import gateway.br.com.gateway.domain.model.consulta.Consulta;
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
