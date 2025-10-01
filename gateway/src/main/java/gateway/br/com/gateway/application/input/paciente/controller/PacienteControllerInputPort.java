package gateway.br.com.gateway.application.input.paciente.controller;

import gateway.br.com.gateway.application.dto.paciente.AtualizaPacienteDTO;
import gateway.br.com.gateway.application.dto.paciente.CadastraPacienteDTO;
import gateway.br.com.gateway.application.dto.paciente.PacienteResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;

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
