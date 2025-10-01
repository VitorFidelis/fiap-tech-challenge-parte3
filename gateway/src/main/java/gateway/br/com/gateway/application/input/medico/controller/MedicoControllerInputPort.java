package gateway.br.com.gateway.application.input.medico.controller;
import java.util.UUID;

import gateway.br.com.gateway.application.dto.medico.AtualizaMedicoDTO;
import gateway.br.com.gateway.application.dto.medico.CadastraMedicoDTO;
import gateway.br.com.gateway.application.dto.medico.EncontraMedicosPorEspecialidadeRequestDTO;
import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;

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
