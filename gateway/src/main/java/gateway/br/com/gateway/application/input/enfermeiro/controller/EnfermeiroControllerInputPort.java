package gateway.br.com.gateway.application.input.enfermeiro.controller;

import gateway.br.com.gateway.application.dto.enfermeiro.AtualizaEnfermeiroDTO;
import gateway.br.com.gateway.application.dto.enfermeiro.CadastraEnfermeiroDTO;
import gateway.br.com.gateway.application.dto.enfermeiro.EnfermeiroResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;

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
