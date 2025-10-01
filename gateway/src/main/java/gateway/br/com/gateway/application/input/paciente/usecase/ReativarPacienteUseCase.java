package gateway.br.com.gateway.application.input.paciente.usecase;

import java.util.UUID;

public interface ReativarPacienteUseCase {
    void executar(UUID id);
}
