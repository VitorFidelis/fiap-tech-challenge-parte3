package com.agendio_api.agendamento.application.usecase.paciente;

import com.agendio_api.agendamento.application.port.dto.paciente.CadastraPacienteDTO;
import com.agendio_api.agendamento.application.port.dto.paciente.PacienteResponseDTO;
import com.agendio_api.agendamento.application.port.input.paciente.usecase.CadastrarPacienteUseCase;
import com.agendio_api.agendamento.application.port.mapper.paciente.IPacienteMapper;
import com.agendio_api.agendamento.application.port.output.usuario.paciente.PacienteGateway;
import com.agendio_api.agendamento.domain.model.usuario.Paciente;
import com.agendio_api.agendamento.domain.validator.PacienteValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CadastrarPacienteUseCaseImpl implements CadastrarPacienteUseCase {
    private final PacienteGateway pacienteGateway;
    private final IPacienteMapper pacienteMapper;
    private final List<PacienteValidator> pacienteValidators;
    private final BCryptPasswordEncoder passwordEncoder;

    public CadastrarPacienteUseCaseImpl(PacienteGateway pacienteGateway, IPacienteMapper pacienteMapper, List<PacienteValidator> pacienteValidators, BCryptPasswordEncoder passwordEncoder) {
        this.pacienteGateway = pacienteGateway;
        this.pacienteMapper = pacienteMapper;
        this.pacienteValidators = pacienteValidators;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public PacienteResponseDTO executar(CadastraPacienteDTO cadastraPacienteDTO) {
        Paciente paciente = pacienteMapper.toDomain(cadastraPacienteDTO);
        validaPaciente(paciente);
        paciente.setSenha(passwordEncoder.encode(cadastraPacienteDTO.senha()));
        paciente = pacienteGateway.cadastrar(paciente);
        return pacienteMapper.toResponseDTO(paciente);
    }

    private void validaPaciente(Paciente paciente) {
        for (PacienteValidator validator : pacienteValidators) {
            validator.validar(paciente);
        }
    }
}
