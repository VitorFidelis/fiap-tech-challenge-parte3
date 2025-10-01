package gateway.br.com.gateway.application.input.paciente.usecase;

import gateway.br.com.gateway.application.dto.paciente.PacienteResponseDTO;
import gateway.br.com.gateway.application.dto.paciente.CadastraPacienteDTO;

public interface CadastrarPacienteUseCase {
    PacienteResponseDTO executar(CadastraPacienteDTO cadastraPacienteDTO);


}
