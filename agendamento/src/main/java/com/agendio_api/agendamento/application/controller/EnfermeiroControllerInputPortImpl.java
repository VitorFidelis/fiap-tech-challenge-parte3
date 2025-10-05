package com.agendio_api.agendamento.application.controller;

import com.agendio_api.agendamento.application.port.dto.enfermeiro.AtualizaEnfermeiroDTO;
import com.agendio_api.agendamento.application.port.dto.enfermeiro.CadastraEnfermeiroDTO;
import com.agendio_api.agendamento.application.port.dto.enfermeiro.EnfermeiroResponseDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResponseDTO;
import com.agendio_api.agendamento.application.port.input.enfermeiro.controller.EnfermeiroControllerInputPort;
import com.agendio_api.agendamento.application.port.input.enfermeiro.usecase.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class EnfermeiroControllerInputPortImpl implements EnfermeiroControllerInputPort {
    private static final Logger logger = LoggerFactory.getLogger(EnfermeiroControllerInputPortImpl.class.getName());

    private final AtualizarEnfermeiroUseCase atualizarEnfermeiroUseCase;
    private final CadastrarEnfermeiroUseCase cadastrarEnfermeiroUseCase;
    private final EncontrarEnfermeiroUseCase encontrarEnfermeiroUseCase;
    private final EncontrarTodosEnfermeirosUseCase encontrarTodosEnfermeirosUseCase;
    private final ReativarEnfermeiroUseCase reativarEnfermeiroUseCase;
    private final DesativarEnfermeiroUseCase desativarEnfermeiroUseCase;

    public EnfermeiroControllerInputPortImpl(AtualizarEnfermeiroUseCase atualizarEnfermeiroUseCase, CadastrarEnfermeiroUseCase cadastrarEnfermeiroUseCase, EncontrarEnfermeiroUseCase encontrarEnfermeiroUseCase, EncontrarTodosEnfermeirosUseCase encontrarTodosEnfermeirosUseCase, ReativarEnfermeiroUseCase reativarEnfermeiroUseCase, DesativarEnfermeiroUseCase desativarEnfermeiroUseCase) {
        this.atualizarEnfermeiroUseCase = atualizarEnfermeiroUseCase;
        this.cadastrarEnfermeiroUseCase = cadastrarEnfermeiroUseCase;
        this.encontrarEnfermeiroUseCase = encontrarEnfermeiroUseCase;
        this.encontrarTodosEnfermeirosUseCase = encontrarTodosEnfermeirosUseCase;
        this.reativarEnfermeiroUseCase = reativarEnfermeiroUseCase;
        this.desativarEnfermeiroUseCase = desativarEnfermeiroUseCase;
    }

    @Override
    public EnfermeiroResponseDTO cadastrar(CadastraEnfermeiroDTO cadastraEnfermeiroDTO) {
        logger.info("Cadastrando enfermeiro -> {} ", cadastraEnfermeiroDTO);
        return cadastrarEnfermeiroUseCase.execute(cadastraEnfermeiroDTO);
    }

    @Override
    public EnfermeiroResponseDTO atualizar(UUID id, AtualizaEnfermeiroDTO atualizaEnfermeiroDTO) {
        logger.info("Atualizando enfermeiro com id {} ", id);
        return atualizarEnfermeiroUseCase.execute(id, atualizaEnfermeiroDTO);
    }

    @Override
    public EnfermeiroResponseDTO encontrarPorId(UUID id) {
        logger.info("Encontrando enfermeiro com id {} ", id);
        return encontrarEnfermeiroUseCase.execute(id);
    }

    @Override
    public PaginatedResponseDTO<EnfermeiroResponseDTO> listarTodos(
            PaginatedRequestDTO paginacao) {
        logger.info("Listando todos os enfermeiros");
        return encontrarTodosEnfermeirosUseCase.executar(paginacao);
    }

    @Override
    public void desativar(UUID id) {
        logger.info("Desativando enfermeiro com id {} ", id);
        desativarEnfermeiroUseCase.execute(id);
    }

    @Override
    public void reativar(UUID id) {
        logger.info("Reativando enfermeiro com id {} ", id);
        reativarEnfermeiroUseCase.executar(id);
    }
}
