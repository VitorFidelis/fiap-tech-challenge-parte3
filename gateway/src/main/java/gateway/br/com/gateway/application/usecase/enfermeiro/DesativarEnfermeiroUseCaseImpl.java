package gateway.br.com.gateway.application.usecase.enfermeiro;

import gateway.br.com.gateway.application.input.enfermeiro.usecase.DesativarEnfermeiroUseCase;
import gateway.br.com.gateway.application.output.usuario.enfermeiro.EnfermeiroGateway;
import gateway.br.com.gateway.domain.exception.UsuarioNaoEncontradoException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class DesativarEnfermeiroUseCaseImpl implements DesativarEnfermeiroUseCase {
    private final EnfermeiroGateway enfermeiroGateway;

    public DesativarEnfermeiroUseCaseImpl(EnfermeiroGateway enfermeiroGateway) {
        this.enfermeiroGateway = enfermeiroGateway;
    }

    @Transactional
    @Override
    public void execute(UUID id) {
        if (enfermeiroGateway.buscarPorId(id).isEmpty())
            throw new UsuarioNaoEncontradoException("Enfermeiro não encontrado.");
        enfermeiroGateway.desativar(id);
    }
}
