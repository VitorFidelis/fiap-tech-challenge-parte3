package gateway.br.com.gateway.application.input.consulta.controller;

import gateway.br.com.gateway.application.dto.consulta.AgendaConsultaDTO;
import gateway.br.com.gateway.application.dto.consulta.AtualizaConsultaDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaFiltroRequestDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;
import gateway.br.com.gateway.application.dto.usuarios.UsuarioIdFiltroPaginadoRequestDTO;

import java.util.UUID;

public interface ConsultaControllerInputPort {
    void cancelar(UUID id, String motivo);

    ConsultaResponseDTO atualizar(UUID id, AtualizaConsultaDTO atualizaConsultaDTO);

    ConsultaResponseDTO agendar(AgendaConsultaDTO agendaConsultaDTO);

    ConsultaResponseDTO encontrarPorId(UUID id);

    PaginatedResponseDTO<ConsultaResponseDTO> listarTodas(PaginatedRequestDTO paginacao);

    PaginatedResponseDTO<ConsultaResponseDTO> listarPorPeriodo(ConsultaFiltroRequestDTO filtro, PaginatedRequestDTO paginacao);

    PaginatedResponseDTO<ConsultaResponseDTO> listarPorPaciente(UsuarioIdFiltroPaginadoRequestDTO request);

    PaginatedResponseDTO<ConsultaResponseDTO> listarPorMedico(UsuarioIdFiltroPaginadoRequestDTO request);

    PaginatedResponseDTO<ConsultaResponseDTO> listarPorEnfermeiro(UsuarioIdFiltroPaginadoRequestDTO request);


}
