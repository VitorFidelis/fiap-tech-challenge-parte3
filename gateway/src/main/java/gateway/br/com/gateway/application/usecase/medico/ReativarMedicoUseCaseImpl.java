package gateway.br.com.gateway.application.usecase.medico;

import gateway.br.com.gateway.application.input.medico.usecase.ReativarMedicoUseCase;
import gateway.br.com.gateway.application.output.usuario.medico.MedicoGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class ReativarMedicoUseCaseImpl implements ReativarMedicoUseCase {
    private final MedicoGateway medicoGateway;

    public ReativarMedicoUseCaseImpl(MedicoGateway medicoGateway) {
        this.medicoGateway = medicoGateway;
    }

    @Transactional
    @Override
    public void executar(UUID id) {
        medicoGateway.reativar(id);
    }
}
