package gateway.br.com.gateway.application.controller;

import gateway.br.com.gateway.application.dto.consulta.AgendaConsultaDTO;
import gateway.br.com.gateway.application.dto.consulta.AtualizaConsultaDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaFiltroRequestDTO;
import gateway.br.com.gateway.application.dto.consulta.ConsultaResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;
import gateway.br.com.gateway.application.dto.usuarios.UsuarioIdFiltroPaginadoRequestDTO;
import gateway.br.com.gateway.application.input.consulta.controller.ConsultaControllerInputPort;
import gateway.br.com.gateway.application.input.consulta.usecase.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public ConsultaControllerInputPortImpl(AgendarConsultaUseCase agendarConsultaUseCase, AtualizarConsultaUseCase atualizarConsultaUseCase, CancelarConsultaUseCase cancelarConsultaUseCase, EncontrarConsultaPorIdUseCase encontrarConsultaUseCase, EncontrarConsultasPorEnfermeiroUseCase encontrarConsultasPorEnfermeiroUseCase, EncontrarConsultasPorMedicoUseCase encontrarConsultasPorMedicoUseCase, EncontrarConsultasPorMedicoEPeriodoUseCase encontrarConsultasPorMedicoEPeriodoUseCase, EncontrarConsultasPorPacienteUseCase encontrarConsultasPorPacienteUseCase, EncontrarTodasConsultasUseCase encontrarTodasConsultasUseCase) {
        this.agendarConsultaUseCase = agendarConsultaUseCase;
        this.atualizarConsultaUseCase = atualizarConsultaUseCase;
        this.cancelarConsultaUseCase = cancelarConsultaUseCase;
        this.encontrarConsultaUseCase = encontrarConsultaUseCase;
        this.encontrarConsultasPorEnfermeiroUseCase = encontrarConsultasPorEnfermeiroUseCase;
        this.encontrarConsultasPorMedicoUseCase = encontrarConsultasPorMedicoUseCase;
        this.encontrarConsultasPorMedicoEPeriodoUseCase = encontrarConsultasPorMedicoEPeriodoUseCase;
        this.encontrarConsultasPorPacienteUseCase = encontrarConsultasPorPacienteUseCase;
        this.encontrarTodasConsultasUseCase = encontrarTodasConsultasUseCase;
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
}
