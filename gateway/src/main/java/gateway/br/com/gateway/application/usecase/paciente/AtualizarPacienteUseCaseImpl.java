package gateway.br.com.gateway.application.usecase.paciente;

import gateway.br.com.gateway.application.dto.paciente.AtualizaPacienteDTO;
import gateway.br.com.gateway.application.dto.paciente.PacienteResponseDTO;
import gateway.br.com.gateway.application.input.paciente.usecase.AtualizarPacienteUseCase;
import gateway.br.com.gateway.application.mapper.paciente.IPacienteMapper;
import gateway.br.com.gateway.application.output.usuario.paciente.PacienteGateway;
import gateway.br.com.gateway.domain.exception.UsuarioNaoEncontradoException;
import gateway.br.com.gateway.domain.model.usuario.Paciente;
import gateway.br.com.gateway.domain.model.validator.PacienteValidator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public class AtualizarPacienteUseCaseImpl implements AtualizarPacienteUseCase {
    private final PacienteGateway pacienteGateway;
    private final IPacienteMapper pacienteMapper;
    private final List<PacienteValidator> pacienteValidators;

    public AtualizarPacienteUseCaseImpl(PacienteGateway pacienteGateway, IPacienteMapper pacienteMapper, List<PacienteValidator> pacienteValidators) {
        this.pacienteGateway = pacienteGateway;
        this.pacienteMapper = pacienteMapper;
        this.pacienteValidators = pacienteValidators;
    }

    @Transactional
    @Override
    public PacienteResponseDTO executar(UUID id, AtualizaPacienteDTO atualizaPacienteDTO) {
        Paciente pacienteExistente = pacienteGateway.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Paciente não encontrado."));
        Paciente pacienteParaValidar = pacienteMapper.toDomain(atualizaPacienteDTO, pacienteExistente);
        pacienteValidators.forEach(v -> v.validar(pacienteParaValidar));
        Paciente pacienteParaSalvar = pacienteGateway.atualizar(pacienteParaValidar);
        return pacienteMapper.toResponseDTO(pacienteParaSalvar);
    }
}
