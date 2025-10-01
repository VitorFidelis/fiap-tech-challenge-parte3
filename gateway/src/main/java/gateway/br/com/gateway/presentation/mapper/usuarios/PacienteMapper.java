package gateway.br.com.gateway.presentation.mapper.usuarios;

import gateway.br.com.gateway.application.dto.paciente.AtualizaPacienteDTO;
import gateway.br.com.gateway.application.dto.paciente.CadastraPacienteDTO;
import gateway.br.com.gateway.application.dto.paciente.PacienteResponseDTO;
import gateway.br.com.gateway.application.mapper.paciente.IPacienteMapper;
import gateway.br.com.gateway.domain.model.usuario.Paciente;
import gateway.br.com.gateway.infrastructure.datasource.jpa.paciente.JpaPacienteEntity;
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
                jpaPacienteEntity.getAtualizadoEm());
    }
}
