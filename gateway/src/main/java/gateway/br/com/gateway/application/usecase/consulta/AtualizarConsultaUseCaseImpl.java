package gateway.br.com.gateway.application.usecase.consulta;


import gateway.br.com.gateway.application.dto.consulta.AtualizaConsultaDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;
import gateway.br.com.gateway.application.input.consulta.usecase.AtualizarConsultaUseCase;
import gateway.br.com.gateway.application.mapper.consulta.IConsultaMapper;
import gateway.br.com.gateway.application.output.consulta.ConsultaGateway;
import gateway.br.com.gateway.domain.exception.ConsultaNaoEncontradaException;
import gateway.br.com.gateway.domain.model.consulta.Consulta;
import gateway.br.com.gateway.domain.model.validator.ConsultaValidator;

import java.util.List;
import java.util.UUID;

public class AtualizarConsultaUseCaseImpl implements AtualizarConsultaUseCase {
    private final ConsultaGateway consultaGateway;
    private final IConsultaMapper consultaMapper;
    private final List<ConsultaValidator> consultaValidators;

    public AtualizarConsultaUseCaseImpl(ConsultaGateway consultaGateway, IConsultaMapper consultaMapper, List<ConsultaValidator> consultaValidators) {
        this.consultaGateway = consultaGateway;
        this.consultaMapper = consultaMapper;
        this.consultaValidators = consultaValidators;
    }

    @Override
    public ConsultaResponseDTO executar(UUID id, AtualizaConsultaDTO atualizaConsultaDTO) {
        Consulta consultaExistente = consultaGateway.buscarPorId(id)
                .orElseThrow(() -> new ConsultaNaoEncontradaException("Consulta com ID " + id + " nao encontrada."));

        Consulta consultaAtualizada = consultaMapper.toDomain(atualizaConsultaDTO, consultaExistente);
        consultaValidators.forEach(validator -> validator.validar(consultaAtualizada));
        Consulta consultaSalva = consultaGateway.atualizar(consultaAtualizada);
        return consultaMapper.toResponseDTO(consultaSalva);
    }
}
