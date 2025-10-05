package com.agendio_api.agendamento.application.usecase.paciente;

import com.agendio_api.agendamento.application.port.dto.paciente.AtualizaPacienteDTO;
import com.agendio_api.agendamento.application.port.dto.paciente.PacienteResponseDTO;
import com.agendio_api.agendamento.application.port.input.paciente.usecase.AtualizarPacienteUseCase;
import com.agendio_api.agendamento.application.port.mapper.paciente.IPacienteMapper;
import com.agendio_api.agendamento.application.port.output.usuario.paciente.PacienteGateway;
import com.agendio_api.agendamento.domain.exception.UsuarioNaoEncontradoException;
import com.agendio_api.agendamento.domain.model.usuario.Paciente;
import com.agendio_api.agendamento.domain.validator.PacienteValidator;
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
