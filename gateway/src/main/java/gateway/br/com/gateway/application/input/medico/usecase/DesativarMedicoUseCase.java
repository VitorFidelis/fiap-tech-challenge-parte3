package gateway.br.com.gateway.application.input.medico.usecase;

import java.util.UUID;

public interface DesativarMedicoUseCase {
    void executar(UUID id);
}
