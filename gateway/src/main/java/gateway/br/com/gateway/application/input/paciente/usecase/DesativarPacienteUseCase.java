package gateway.br.com.gateway.application.input.paciente.usecase;

import java.util.UUID;

public interface DesativarPacienteUseCase {
    void executar(UUID id);
}
