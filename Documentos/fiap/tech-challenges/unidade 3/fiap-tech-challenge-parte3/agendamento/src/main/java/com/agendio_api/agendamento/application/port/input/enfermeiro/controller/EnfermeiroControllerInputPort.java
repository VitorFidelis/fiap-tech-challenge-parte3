package com.agendio_api.agendamento.application.port.input.enfermeiro.controller;

import com.agendio_api.agendamento.application.port.dto.enfermeiro.AtualizaEnfermeiroDTO;
import com.agendio_api.agendamento.application.port.dto.enfermeiro.CadastraEnfermeiroDTO;
import com.agendio_api.agendamento.application.port.dto.enfermeiro.EnfermeiroResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;

import java.util.UUID;

public interface EnfermeiroControllerInputPort {
    EnfermeiroResponseDTO cadastrar(CadastraEnfermeiroDTO cadastraEnfermeiroDTO);

    EnfermeiroResponseDTO atualizar(UUID id, AtualizaEnfermeiroDTO atualizaEnfermeiroDTO);

    EnfermeiroResponseDTO encontrarPorId(UUID id);

    PaginatedResponseDTO<EnfermeiroResponseDTO> listarTodos(
            PaginatedRequestDTO paginacao);

    void desativar(UUID id);

    void reativar(UUID id);
}
