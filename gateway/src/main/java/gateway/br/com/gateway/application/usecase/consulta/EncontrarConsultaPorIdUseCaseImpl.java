package gateway.br.com.gateway.application.usecase.consulta;

import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;
import gateway.br.com.gateway.application.input.consulta.usecase.EncontrarConsultaPorIdUseCase;
import gateway.br.com.gateway.application.mapper.consulta.IConsultaMapper;
import gateway.br.com.gateway.application.output.consulta.ConsultaGateway;
import gateway.br.com.gateway.domain.exception.ConsultaNaoEncontradaException;

import java.util.UUID;

public class EncontrarConsultaPorIdUseCaseImpl implements EncontrarConsultaPorIdUseCase {
    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;

    public EncontrarConsultaPorIdUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
    }

    @Override
    public ConsultaResponseDTO executar(UUID id) {
        return consultaMapper
                .toResponseDTO(consultaGateway.buscarPorId(id)
                        .orElseThrow(() -> new ConsultaNaoEncontradaException("Consulta com ID " + id + " nao encontrada.")));
    }
}
