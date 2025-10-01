package gateway.br.com.gateway.application.usecase.enfermeiro;

import gateway.br.com.gateway.application.input.enfermeiro.usecase.ReativarEnfermeiroUseCase;
import gateway.br.com.gateway.application.output.usuario.enfermeiro.EnfermeiroGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class ReativarEnfermeiroUseCaseImpl implements ReativarEnfermeiroUseCase {
    private final EnfermeiroGateway enfermeiroGateway;

    public ReativarEnfermeiroUseCaseImpl(EnfermeiroGateway enfermeiroGateway) {
        this.enfermeiroGateway = enfermeiroGateway;
    }

    @Transactional
    @Override
    public void executar(UUID id) {
        enfermeiroGateway.reativar(id);
    }
}
