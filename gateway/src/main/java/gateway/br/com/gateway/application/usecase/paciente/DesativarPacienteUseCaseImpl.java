package gateway.br.com.gateway.application.usecase.paciente;

import gateway.br.com.gateway.application.input.paciente.usecase.DesativarPacienteUseCase;
import gateway.br.com.gateway.application.output.usuario.paciente.PacienteGateway;
import gateway.br.com.gateway.domain.exception.UsuarioNaoEncontradoException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class DesativarPacienteUseCaseImpl implements DesativarPacienteUseCase {
    private final PacienteGateway pacienteGateway;

    public DesativarPacienteUseCaseImpl(PacienteGateway pacienteGateway) {
        this.pacienteGateway = pacienteGateway;
    }

    @Transactional
    @Override
    public void executar(UUID id) {
        if (pacienteGateway.buscarPorId(id).isEmpty())
            throw new UsuarioNaoEncontradoException("Paciente não encontrado.");
        pacienteGateway.desativar(id);
    }
}
