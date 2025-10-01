package gateway.br.com.gateway.application.input.consulta.usecase;

import gateway.br.com.gateway.application.dto.consulta.AtualizaConsultaDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;

import java.util.UUID;

public interface AtualizarConsultaUseCase {
    ConsultaResponseDTO executar(UUID id, AtualizaConsultaDTO atualizaConsultaDTO);
}
