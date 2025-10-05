package com.agendio_api.agendamento.application.usecase.paciente;

import com.agendio_api.agendamento.application.port.dto.paciente.PacienteResponseDTO;
import com.agendio_api.agendamento.application.port.input.paciente.usecase.EncontrarPacienteUseCase;
import com.agendio_api.agendamento.application.port.mapper.paciente.IPacienteMapper;
import com.agendio_api.agendamento.application.port.output.usuario.paciente.PacienteGateway;
import com.agendio_api.agendamento.domain.exception.UsuarioNaoEncontradoException;

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
