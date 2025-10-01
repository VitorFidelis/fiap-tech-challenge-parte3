package gateway.br.com.gateway.application.usecase.paciente;

import gateway.br.com.gateway.application.dto.paciente.CadastraPacienteDTO;
import gateway.br.com.gateway.application.dto.paciente.PacienteResponseDTO;
import gateway.br.com.gateway.application.input.paciente.usecase.CadastrarPacienteUseCase;
import gateway.br.com.gateway.application.mapper.paciente.IPacienteMapper;
import gateway.br.com.gateway.application.output.usuario.paciente.PacienteGateway;
import gateway.br.com.gateway.domain.model.usuario.Paciente;
import gateway.br.com.gateway.domain.model.validator.PacienteValidator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CadastrarPacienteUseCaseImpl implements CadastrarPacienteUseCase {
    private final PacienteGateway pacienteGateway;
    private final IPacienteMapper pacienteMapper;
    private final List<PacienteValidator> pacienteValidators;

    public CadastrarPacienteUseCaseImpl(PacienteGateway pacienteGateway, IPacienteMapper pacienteMapper, List<PacienteValidator> pacienteValidators) {
        this.pacienteGateway = pacienteGateway;
        this.pacienteMapper = pacienteMapper;
        this.pacienteValidators = pacienteValidators;
    }

    @Transactional
    @Override
    public PacienteResponseDTO executar(CadastraPacienteDTO cadastraPacienteDTO) {
        Paciente paciente = pacienteMapper.toDomain(cadastraPacienteDTO);
        validaPaciente(paciente);
        paciente = pacienteGateway.cadastrar(paciente);
        return pacienteMapper.toResponseDTO(paciente);

    }

    private void validaPaciente(Paciente paciente) {
        for (PacienteValidator validator : pacienteValidators) {
            validator.validar(paciente);
        }
    }
}
