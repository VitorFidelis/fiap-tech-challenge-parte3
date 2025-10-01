package gateway.br.com.gateway.application.usecase.paciente;

import gateway.br.com.gateway.application.dto.paciente.PacienteResponseDTO;
import gateway.br.com.gateway.application.input.paciente.usecase.EncontrarPacienteUseCase;
import gateway.br.com.gateway.application.mapper.paciente.IPacienteMapper;
import gateway.br.com.gateway.application.output.usuario.paciente.PacienteGateway;
import gateway.br.com.gateway.domain.exception.UsuarioNaoEncontradoException;

import java.util.UUID;

public class EncontrarPacienteUseCaseImpl implements EncontrarPacienteUseCase {
    private final PacienteGateway pacienteGateway;
    private final IPacienteMapper pacienteMapper;

    public EncontrarPacienteUseCaseImpl(PacienteGateway pacienteGateway, IPacienteMapper pacienteMapper) {
        this.pacienteGateway = pacienteGateway;
        this.pacienteMapper = pacienteMapper;
    }

    @Override
    public PacienteResponseDTO executar(UUID id) {
        return pacienteMapper.toResponseDTO(pacienteGateway.buscarPorId(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Paciente não encontrado")));
    }
}
