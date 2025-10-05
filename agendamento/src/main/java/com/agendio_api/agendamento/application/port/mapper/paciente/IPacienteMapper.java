package com.agendio_api.agendamento.application.port.mapper.paciente;

import com.agendio_api.agendamento.application.port.dto.paciente.AtualizaPacienteDTO;
import com.agendio_api.agendamento.application.port.dto.paciente.CadastraPacienteDTO;
import com.agendio_api.agendamento.application.port.dto.paciente.PacienteResponseDTO;
import com.agendio_api.agendamento.domain.model.usuario.Paciente;
import com.agendio_api.agendamento.infrastructure.datasource.jpa.paciente.JpaPacienteEntity;

import java.util.List;

public interface IPacienteMapper {
    List<PacienteResponseDTO> mapList(List<Paciente> pacientes);

    PacienteResponseDTO toResponseDTO(Paciente paciente);

    JpaPacienteEntity toJpaPacienteEntity(Paciente paciente);

    Paciente toDomain(CadastraPacienteDTO cadastraPacienteDTO);

    Paciente toDomain(AtualizaPacienteDTO atualizaPacienteDTO, Paciente pacienteExistente);

    Paciente toDomain(JpaPacienteEntity jpaPacienteEntity);
}
