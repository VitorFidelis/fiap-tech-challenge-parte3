package com.agendio_api.agendamento.application.controller;

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
import com.agendio_api.agendamento.application.port.input.consulta.controller.ConsultaControllerInputPort;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.*;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.graphql.AtualizarConsultaGraphqlUseCase;
import com.agendio_api.agendamento.application.port.input.consulta.usecase.graphql.ListarConsultasGraphqlUseCase;
import com.agendio_api.agendamento.domain.model.usuario.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class ConsultaControllerInputPortImpl implements ConsultaControllerInputPort {
    private static final Logger logger = LoggerFactory.getLogger(ConsultaControllerInputPortImpl.class.getName());

    private final AgendarConsultaUseCase agendarConsultaUseCase;
    private final AtualizarConsultaUseCase atualizarConsultaUseCase;
    private final CancelarConsultaUseCase cancelarConsultaUseCase;
    private final EncontrarConsultaPorIdUseCase encontrarConsultaUseCase;
    private final EncontrarConsultasPorEnfermeiroUseCase encontrarConsultasPorEnfermeiroUseCase;
    private final EncontrarConsultasPorMedicoUseCase encontrarConsultasPorMedicoUseCase;
    private final EncontrarConsultasPorMedicoEPeriodoUseCase encontrarConsultasPorMedicoEPeriodoUseCase;
    private final EncontrarConsultasPorPacienteUseCase encontrarConsultasPorPacienteUseCase;
    private final EncontrarTodasConsultasUseCase encontrarTodasConsultasUseCase;
    private final ListarConsultasGraphqlUseCase listarConsultasGraphqlUseCase;
    private final AtualizarConsultaGraphqlUseCase atualizarConsultaGraphqlUseCase;

    //injetar serviço que pega o usuário autenticado

    public ConsultaControllerInputPortImpl(AgendarConsultaUseCase agendarConsultaUseCase, AtualizarConsultaUseCase atualizarConsultaUseCase, CancelarConsultaUseCase cancelarConsultaUseCase, EncontrarConsultaPorIdUseCase encontrarConsultaUseCase, EncontrarConsultasPorEnfermeiroUseCase encontrarConsultasPorEnfermeiroUseCase, EncontrarConsultasPorMedicoUseCase encontrarConsultasPorMedicoUseCase, EncontrarConsultasPorMedicoEPeriodoUseCase encontrarConsultasPorMedicoEPeriodoUseCase, EncontrarConsultasPorPacienteUseCase encontrarConsultasPorPacienteUseCase, EncontrarTodasConsultasUseCase encontrarTodasConsultasUseCase, ListarConsultasGraphqlUseCase listarConsultasGraphqlUseCase, AtualizarConsultaGraphqlUseCase atualizarConsultaGraphqlUseCase) {
        this.agendarConsultaUseCase = agendarConsultaUseCase;
        this.atualizarConsultaUseCase = atualizarConsultaUseCase;
        this.cancelarConsultaUseCase = cancelarConsultaUseCase;
        this.encontrarConsultaUseCase = encontrarConsultaUseCase;
        this.encontrarConsultasPorEnfermeiroUseCase = encontrarConsultasPorEnfermeiroUseCase;
        this.encontrarConsultasPorMedicoUseCase = encontrarConsultasPorMedicoUseCase;
        this.encontrarConsultasPorMedicoEPeriodoUseCase = encontrarConsultasPorMedicoEPeriodoUseCase;
        this.encontrarConsultasPorPacienteUseCase = encontrarConsultasPorPacienteUseCase;
        this.encontrarTodasConsultasUseCase = encontrarTodasConsultasUseCase;
        this.listarConsultasGraphqlUseCase = listarConsultasGraphqlUseCase;
        this.atualizarConsultaGraphqlUseCase = atualizarConsultaGraphqlUseCase;
    }

    @Override
    public void cancelar(UUID id, String motivo) {
        logger.info("Cancelando consulta {}", id);
        cancelarConsultaUseCase.executar(id, motivo);
    }

    @Override
    public ConsultaResponseDTO atualizar(UUID id, AtualizaConsultaDTO atualizaConsultaDTO) {
        logger.info("Atualizando consulta {}", id);
        return atualizarConsultaUseCase.executar(id, atualizaConsultaDTO);
    }

    @Override
    public ConsultaResponseDTO agendar(AgendaConsultaDTO agendaConsultaDTO) {
        logger.info("Agendando consulta {}", agendaConsultaDTO);
        return agendarConsultaUseCase.executar(agendaConsultaDTO);
    }

    @Override
    public ConsultaResponseDTO encontrarPorId(UUID id) {
        logger.info("Encontrando consulta {}", id);
        return encontrarConsultaUseCase.executar(id);
    }

    @Override
    public PaginatedResponseDTO<ConsultaResponseDTO> listarTodas(PaginatedRequestDTO paginacao) {
        logger.info("Listando todas as consultas");
        return encontrarTodasConsultasUseCase.executar(paginacao);
    }

    @Override
    public PaginatedResponseDTO<ConsultaResponseDTO> listarPorPeriodo(ConsultaFiltroRequestDTO filtro, PaginatedRequestDTO paginacao) {
        logger.info("Listando consultas por médico {} e periodo {} {}", filtro.medicoId(), filtro.inicio(), filtro.fim());
        return encontrarConsultasPorMedicoEPeriodoUseCase.executar(filtro, paginacao);
    }

    @Override
    public PaginatedResponseDTO<ConsultaResponseDTO> listarPorPaciente(UsuarioIdFiltroPaginadoRequestDTO request) {
        logger.info("Listando consultas por paciente {}", request.usuarioId());
        return encontrarConsultasPorPacienteUseCase.executar(request);
    }

    @Override
    public PaginatedResponseDTO<ConsultaResponseDTO> listarPorMedico(UsuarioIdFiltroPaginadoRequestDTO request) {
        logger.info("Listando consultas por médico {}", request.usuarioId());
        return encontrarConsultasPorMedicoUseCase.executar(request);
    }

    @Override
    public PaginatedResponseDTO<ConsultaResponseDTO> listarPorEnfermeiro(UsuarioIdFiltroPaginadoRequestDTO request) {
        logger.info("Listando consultas por enfermeiro {}", request.usuarioId());
        return encontrarConsultasPorEnfermeiroUseCase.executar(request);
    }

    @Override
    public List<ConsultaResponseGraphqlDTO> listarPorFiltros(ListarConsultaGraphqlDTO filtro) {
        logger.info("Listando consultas flexíveis do graphql: {}", filtro);
        Usuario usuarioLogado = null;
        return listarConsultasGraphqlUseCase.executar(filtro, usuarioLogado);
    }

    @Override
    public ConsultaResponseGraphqlDTO atualizarGraphql(AtualizarConsultaGraphqlDTO request) {
        logger.info("Atualizando consulta id: {}", request.id());
        Usuario usuarioLogado = null;
        return atualizarConsultaGraphqlUseCase.executar(request, usuarioLogado);
    }
}
