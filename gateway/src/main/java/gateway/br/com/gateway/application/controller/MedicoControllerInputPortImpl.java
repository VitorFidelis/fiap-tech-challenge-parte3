package gateway.br.com.gateway.application.controller;

import gateway.br.com.gateway.application.dto.medico.AtualizaMedicoDTO;
import gateway.br.com.gateway.application.dto.medico.CadastraMedicoDTO;
import gateway.br.com.gateway.application.dto.medico.EncontraMedicosPorEspecialidadeRequestDTO;
import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResponseDTO;
import gateway.br.com.gateway.application.input.medico.controller.MedicoControllerInputPort;
import gateway.br.com.gateway.application.input.medico.usecase.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class MedicoControllerInputPortImpl implements MedicoControllerInputPort {
    private static final Logger logger = LoggerFactory.getLogger(MedicoControllerInputPortImpl.class.getName());
    private final AtualizarMedicoUseCase atualizarMedicoUseCase;
    private final CadastrarMedicoUseCase cadastrarMedicoUseCase;
    private final DesativarMedicoUseCase desativarMedicoUseCase;
    private final EncontrarMedicoPorCRMUseCase encontrarMedicoPorCRMUseCase;
    private final EncontrarMedicoPorIdUseCase encontrarMedicoPorIdUseCase;
    private final EncontrarMedicosPorEspecialidadeUseCase encontrarMedicosPorEspecialidadeUseCase;
    private final EncontrarTodosMedicosUseCase encontrarTodosMedicosUseCase;
    private final ReativarMedicoUseCase reativarMedicoUseCase;

    public MedicoControllerInputPortImpl(AtualizarMedicoUseCase atualizarMedicoUseCase, CadastrarMedicoUseCase cadastrarMedicoUseCase, DesativarMedicoUseCase desativarMedicoUseCase, EncontrarMedicoPorCRMUseCase encontrarMedicoPorCRMUseCase, EncontrarMedicoPorIdUseCase encontrarMedicoPorIdUseCase, EncontrarMedicosPorEspecialidadeUseCase encontrarMedicosPorEspecialidadeUseCase, EncontrarTodosMedicosUseCase encontrarTodosMedicosUseCase, ReativarMedicoUseCase reativarMedicoUseCase) {
        this.atualizarMedicoUseCase = atualizarMedicoUseCase;
        this.cadastrarMedicoUseCase = cadastrarMedicoUseCase;
        this.desativarMedicoUseCase = desativarMedicoUseCase;
        this.encontrarMedicoPorCRMUseCase = encontrarMedicoPorCRMUseCase;
        this.encontrarMedicoPorIdUseCase = encontrarMedicoPorIdUseCase;
        this.encontrarMedicosPorEspecialidadeUseCase = encontrarMedicosPorEspecialidadeUseCase;
        this.encontrarTodosMedicosUseCase = encontrarTodosMedicosUseCase;
        this.reativarMedicoUseCase = reativarMedicoUseCase;
    }

    @Override
    public MedicoResponseDTO cadastrar(CadastraMedicoDTO cadastraMedicoDTO) {
        logger.info("Cadastrando medico {}", cadastraMedicoDTO);
        return cadastrarMedicoUseCase.executar(cadastraMedicoDTO);
    }

    @Override
    public MedicoResponseDTO atualizar(UUID id, AtualizaMedicoDTO atualizaMedicoDTO) {
        logger.info("Atualizando medico {}", id);
        return atualizarMedicoUseCase.executar(id, atualizaMedicoDTO);
    }

    @Override
    public MedicoResponseDTO encontrarPorId(UUID id) {
        logger.info("Encontrando medico com id: {}", id);
        return encontrarMedicoPorIdUseCase.executar(id);
    }

    @Override
    public MedicoResponseDTO encontrarPorCRM(String crm) {
        logger.info("Encontrando medico com crm:{}", crm);
        return encontrarMedicoPorCRMUseCase.executar(crm);
    }

    @Override
    public PaginatedResponseDTO<MedicoResponseDTO> listarPorEspecialidade(EncontraMedicosPorEspecialidadeRequestDTO request) {
        logger.info("Encontrando medicos com especialidade:{}", request.especialidade());
        return encontrarMedicosPorEspecialidadeUseCase.executar(request);
    }


    @Override
    public PaginatedResponseDTO<MedicoResponseDTO> listarTodos(PaginatedRequestDTO paginacao) {
        logger.info("Listando todos os medicos");
        return encontrarTodosMedicosUseCase.executar(paginacao);
    }

    @Override
    public void desativar(UUID id) {
        logger.info("Desativando medico com id {}", id);
        desativarMedicoUseCase.executar(id);
    }

    @Override
    public void reativar(UUID id) {
        logger.info("Reativando medico com id {}", id);
        reativarMedicoUseCase.executar(id);
    }
}
