package com.agendio_api.agendamento.application.port.input.medico.controller;

import com.agendio_api.agendamento.application.port.dto.medico.AtualizaMedicoDTO;
import com.agendio_api.agendamento.application.port.dto.medico.CadastraMedicoDTO;
import com.agendio_api.agendamento.application.port.dto.medico.EncontraMedicosPorEspecialidadeRequestDTO;
import com.agendio_api.agendamento.application.port.dto.medico.MedicoResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;

import java.util.UUID;

public interface MedicoControllerInputPort {
    MedicoResponseDTO cadastrar(CadastraMedicoDTO cadastraMedicoDTO);

    MedicoResponseDTO atualizar(UUID id, AtualizaMedicoDTO atualizaMedicoDTO);

    MedicoResponseDTO encontrarPorId(UUID id);

    MedicoResponseDTO encontrarPorCRM(String crm);

    PaginatedResponseDTO<MedicoResponseDTO> listarPorEspecialidade(EncontraMedicosPorEspecialidadeRequestDTO request);

    PaginatedResponseDTO<MedicoResponseDTO> listarTodos(PaginatedRequestDTO paginacao);

    void desativar(UUID id);

    void reativar(UUID id);
}
