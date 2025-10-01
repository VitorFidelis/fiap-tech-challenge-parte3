package gateway.br.com.gateway.application.input.consulta.usecase;

import java.util.UUID;

public interface CancelarConsultaUseCase {
    void executar(UUID id, String motivoCancelamento);

}
