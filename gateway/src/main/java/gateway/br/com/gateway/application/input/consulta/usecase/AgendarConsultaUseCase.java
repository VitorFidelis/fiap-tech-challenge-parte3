package gateway.br.com.gateway.application.input.consulta.usecase;

import gateway.br.com.gateway.application.dto.consulta.AgendaConsultaDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;

public interface AgendarConsultaUseCase {
    ConsultaResponseDTO executar(AgendaConsultaDTO agendaConsultaDTO);
}
