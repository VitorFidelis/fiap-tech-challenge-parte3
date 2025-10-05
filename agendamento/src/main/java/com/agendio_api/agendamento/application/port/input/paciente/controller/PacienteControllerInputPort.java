package com.agendio_api.agendamento.application.port.input.paciente.controller;

import com.agendio_api.agendamento.application.port.dto.paciente.AtualizaPacienteDTO;
import com.agendio_api.agendamento.application.port.dto.paciente.CadastraPacienteDTO;
import com.agendio_api.agendamento.application.port.dto.paciente.PacienteResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;

import java.util.UUID;

public interface PacienteControllerInputPort {
    PacienteResponseDTO cadastrar(CadastraPacienteDTO dto);

    PacienteResponseDTO atualizar(UUID id, AtualizaPacienteDTO dto);

    PacienteResponseDTO encontrarPorId(UUID id);

    PaginatedResponseDTO<PacienteResponseDTO> encontrarTodos(
            PaginatedRequestDTO paginacao);

    void desativar(UUID id);

    void reativar(UUID id);
}
