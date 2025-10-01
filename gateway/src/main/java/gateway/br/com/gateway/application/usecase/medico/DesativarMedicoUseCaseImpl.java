package gateway.br.com.gateway.application.usecase.medico;

import gateway.br.com.gateway.application.input.medico.usecase.DesativarMedicoUseCase;
import gateway.br.com.gateway.application.output.usuario.medico.MedicoGateway;
import gateway.br.com.gateway.domain.exception.UsuarioNaoEncontradoException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class DesativarMedicoUseCaseImpl implements DesativarMedicoUseCase {
    private final MedicoGateway medicoGateway;

    public DesativarMedicoUseCaseImpl(MedicoGateway medicoGateway) {
        this.medicoGateway = medicoGateway;
    }

    @Transactional
    @Override
    public void executar(UUID id) {
        if (medicoGateway.buscarPorId(id).isEmpty()) throw new UsuarioNaoEncontradoException("Medico não encontrado.");
        medicoGateway.desativar(id);
    }
}
