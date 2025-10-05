package com.agendio_api.agendamento.application.port.input.consulta.controller;

import com.agendio_api.agendamento.application.port.dto.consulta.AgendaConsultaDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.AtualizaConsultaDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaFiltroRequestDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaResponseDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.AtualizarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ConsultaResponseGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ListarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.dto.usuario.UsuarioIdFiltroPaginadoRequestDTO;

import java.util.List;
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

    List<ConsultaResponseGraphqlDTO> listarPorFiltros(ListarConsultaGraphqlDTO filtro);

    ConsultaResponseGraphqlDTO atualizarGraphql(AtualizarConsultaGraphqlDTO input);
}
