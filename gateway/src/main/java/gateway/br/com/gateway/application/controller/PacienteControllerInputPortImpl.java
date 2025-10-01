package gateway.br.com.gateway.application.controller;

import gateway.br.com.gateway.application.dto.paciente.AtualizaPacienteDTO;
import gateway.br.com.gateway.application.dto.paciente.CadastraPacienteDTO;
import gateway.br.com.gateway.application.dto.paciente.PacienteResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;
import gateway.br.com.gateway.application.input.paciente.controller.PacienteControllerInputPort;
import gateway.br.com.gateway.application.input.paciente.usecase.*;
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
