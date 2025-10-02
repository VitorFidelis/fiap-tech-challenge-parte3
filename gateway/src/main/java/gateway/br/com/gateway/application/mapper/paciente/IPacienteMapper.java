package gateway.br.com.gateway.application.mapper.paciente;


import gateway.br.com.gateway.application.dto.paciente.AtualizaPacienteDTO;
import gateway.br.com.gateway.application.dto.paciente.CadastraPacienteDTO;
import gateway.br.com.gateway.application.dto.paciente.PacienteResponseDTO;
import gateway.br.com.gateway.domain.model.usuario.Paciente;
import gateway.br.com.gateway.infrastructure.persistence.entity.usuarios.JpaPacienteEntity;

import java.util.List;

public interface IPacienteMapper {
    List<PacienteResponseDTO> mapList(List<Paciente> pacientes);

    PacienteResponseDTO toResponseDTO(Paciente paciente);

    JpaPacienteEntity toJpaPacienteEntity(Paciente paciente);

    Paciente toDomain(CadastraPacienteDTO cadastraPacienteDTO);

    Paciente toDomain(AtualizaPacienteDTO atualizaPacienteDTO, Paciente pacienteExistente);

    Paciente toDomain(JpaPacienteEntity jpaPacienteEntity);
}
