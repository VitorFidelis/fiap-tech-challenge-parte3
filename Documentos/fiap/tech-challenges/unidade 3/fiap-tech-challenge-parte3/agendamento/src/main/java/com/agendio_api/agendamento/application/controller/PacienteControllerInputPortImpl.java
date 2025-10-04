package com.agendio_api.agendamento.application.controller;

import com.agendio_api.agendamento.application.port.dto.paciente.AtualizaPacienteDTO;
import com.agendio_api.agendamento.application.port.dto.paciente.CadastraPacienteDTO;
import com.agendio_api.agendamento.application.port.dto.paciente.PacienteResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.input.paciente.controller.PacienteControllerInputPort;
import com.agendio_api.agendamento.application.port.input.paciente.usecase.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class PacienteControllerInputPortImpl implements PacienteControllerInputPort {
    private static final Logger logger = LoggerFactory.getLogger(PacienteControllerInputPortImpl.class);

    private final AtualizarPacienteUseCase atualizarPacienteUseCase;
    private final CadastrarPacienteUseCase cadastrarPacienteUseCase;
    private final EncontrarPacienteUseCase encontrarPacienteUseCase;
    private final EncontrarTodosPacientesUseCase encontrarTodosPacientesUseCase;
    private final DesativarPacienteUseCase desativarPacienteUseCase;
    private final ReativarPacienteUseCase reativarPacienteUseCase;

    public PacienteControllerInputPortImpl(AtualizarPacienteUseCase atualizarPacienteUseCase, CadastrarPacienteUseCase cadastrarPacienteUseCase, EncontrarPacienteUseCase encontrarPacienteUseCase, EncontrarTodosPacientesUseCase encontrarTodosPacientesUseCase, DesativarPacienteUseCase desativarPacienteUseCase, ReativarPacienteUseCase reativarPacienteUseCase) {
        this.atualizarPacienteUseCase = atualizarPacienteUseCase;
        this.cadastrarPacienteUseCase = cadastrarPacienteUseCase;
        this.encontrarPacienteUseCase = encontrarPacienteUseCase;
        this.encontrarTodosPacientesUseCase = encontrarTodosPacientesUseCase;
        this.desativarPacienteUseCase = desativarPacienteUseCase;
        this.reativarPacienteUseCase = reativarPacienteUseCase;
    }

    @Override
    public PacienteResponseDTO cadastrar(CadastraPacienteDTO dto) {
        logger.info("Cadastrando paciente -> {}", dto);
        return cadastrarPacienteUseCase.executar(dto);
    }

    @Override
    public PacienteResponseDTO atualizar(UUID id, AtualizaPacienteDTO dto) {
        logger.info("Atualizando paciente -> {}", dto);
        return atualizarPacienteUseCase.executar(id, dto);
    }

    @Override
    public PacienteResponseDTO encontrarPorId(UUID id) {
        logger.info("Encontrando paciente com id {}", id);
        return encontrarPacienteUseCase.executar(id);
    }


    @Override
    public PaginatedResponseDTO<PacienteResponseDTO> encontrarTodos(PaginatedRequestDTO paginacao) {
        logger.info("Listando todos os pacientes");
        return encontrarTodosPacientesUseCase.executar(paginacao);
    }

    @Override
    public void desativar(UUID id) {
        logger.info("Desativando paciente com id {}", id);
        desativarPacienteUseCase.executar(id);
    }

    @Override
    public void reativar(UUID id) {
        logger.info("Reativando paciente com id {}", id);
        reativarPacienteUseCase.executar(id);
    }

}
