package gateway.br.com.gateway.application.usecase.consulta;

import gateway.br.com.gateway.application.input.consulta.usecase.CancelarConsultaUseCase;
import gateway.br.com.gateway.application.output.consulta.ConsultaGateway;
import gateway.br.com.gateway.domain.exception.ConsultaNaoEncontradaException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class CancelarConsultaUseCaseImpl implements CancelarConsultaUseCase {

    private final ConsultaGateway consultaGateway;

    public CancelarConsultaUseCaseImpl(ConsultaGateway consultaGateway) {
        this.consultaGateway = consultaGateway;
    }

    @Transactional
    @Override
    public void executar(UUID id, String motivoCancelamento) {
        if (consultaGateway.buscarPorId(id).isEmpty())
            throw new ConsultaNaoEncontradaException("Consulta com ID " + id + " nao encontrada.");
        consultaGateway.cancelar(id, motivoCancelamento);
    }
}
