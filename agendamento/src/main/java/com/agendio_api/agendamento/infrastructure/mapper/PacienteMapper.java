package com.agendio_api.agendamento.infrastructure.mapper;

import com.agendio_api.agendamento.application.port.dto.paciente.AtualizaPacienteDTO;
import com.agendio_api.agendamento.application.port.dto.paciente.CadastraPacienteDTO;
import com.agendio_api.agendamento.application.port.dto.paciente.PacienteResponseDTO;
import com.agendio_api.agendamento.application.port.mapper.paciente.IPacienteMapper;
import com.agendio_api.agendamento.domain.model.enums.Role;
import com.agendio_api.agendamento.domain.model.usuario.Paciente;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.paciente.JpaPacienteEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PacienteMapper implements IPacienteMapper {
    @Override
    public List<PacienteResponseDTO> mapList(List<Paciente> pacientes) {
        if (pacientes == null || pacientes.isEmpty()) {
            return Collections.emptyList();
        }
        return pacientes.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PacienteResponseDTO toResponseDTO(Paciente paciente) {
        if (paciente == null) return null;
        return new PacienteResponseDTO(paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getDataNascimento(),
                paciente.isAtivo(),
                paciente.getCriadoEm(),
                paciente.getAtualizadoEm());
    }

    @Override
    public JpaPacienteEntity toJpaPacienteEntity(Paciente paciente) {
        JpaPacienteEntity jpaPacienteEntity = new JpaPacienteEntity();
        jpaPacienteEntity.setId(paciente.getId());
        jpaPacienteEntity.setNome(paciente.getNome());
        jpaPacienteEntity.setEmail(paciente.getEmail());
        jpaPacienteEntity.setSenha(paciente.getSenha());
        jpaPacienteEntity.setDataNascimento(paciente.getDataNascimento());
        jpaPacienteEntity.setAtivo(paciente.isAtivo());
        jpaPacienteEntity.setCriadoEm(paciente.getCriadoEm());
        jpaPacienteEntity.setAtualizadoEm(paciente.getAtualizadoEm());
        jpaPacienteEntity.setRole(Role.PACIENTE);
        return jpaPacienteEntity;
    }

    @Override
    public Paciente toDomain(CadastraPacienteDTO cadastraPacienteDTO) {
        return new Paciente(cadastraPacienteDTO.nome(),
                cadastraPacienteDTO.email(),
                cadastraPacienteDTO.senha(),
                cadastraPacienteDTO.dataNascimento());
    }

    @Override
    public Paciente toDomain(AtualizaPacienteDTO atualizaPacienteDTO, Paciente pacienteExistente) {
        pacienteExistente.setNome(atualizaPacienteDTO.nome());
        pacienteExistente.setEmail(atualizaPacienteDTO.email());
        pacienteExistente.setDataNascimento(atualizaPacienteDTO.dataNascimento());
        pacienteExistente.setRole(Role.PACIENTE);
        return pacienteExistente;
    }

    @Override
    public Paciente toDomain(JpaPacienteEntity jpaPacienteEntity) {
        if (jpaPacienteEntity == null) return null;
        return new Paciente(jpaPacienteEntity.getId(),
                jpaPacienteEntity.getNome(),
                jpaPacienteEntity.getEmail(),
                jpaPacienteEntity.getSenha(),
                jpaPacienteEntity.getDataNascimento(),
                jpaPacienteEntity.isAtivo(),
                jpaPacienteEntity.getCriadoEm(),
                jpaPacienteEntity.getAtualizadoEm(),
                jpaPacienteEntity.getRole());
    }
}
