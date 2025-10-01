package gateway.br.com.gateway.application.usecase.paciente;

import gateway.br.com.gateway.application.input.paciente.usecase.ReativarPacienteUseCase;
import gateway.br.com.gateway.application.output.usuario.paciente.PacienteGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class ReativarPacienteUseCaseImpl implements ReativarPacienteUseCase {
    private final PacienteGateway pacienteGateway;

    public ReativarPacienteUseCaseImpl(PacienteGateway pacienteGateway) {
        this.pacienteGateway = pacienteGateway;
    }

    @Transactional
    @Override
    public void executar(UUID id) {
        pacienteGateway.reativar(id);
    }
}
