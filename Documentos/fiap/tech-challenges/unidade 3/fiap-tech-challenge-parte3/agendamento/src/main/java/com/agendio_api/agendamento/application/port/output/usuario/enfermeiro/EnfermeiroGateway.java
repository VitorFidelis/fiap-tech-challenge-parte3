package com.agendio_api.agendamento.application.port.output.usuario.enfermeiro;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.domain.model.usuario.Enfermeiro;

import java.util.Optional;
import java.util.UUID;

/**
 * Porta de saída para operações relacionadas a Enfermeiros.
 * Segue o princípio da Inversão de Dependência (SOLID) ao definir uma abstração
 * que será implementada pela camada de infraestrutura.
 */
public interface EnfermeiroGateway {

    /**
     * Cadastra um novo enfermeiro no sistema.
     *
     * @param enfermeiro O enfermeiro a ser cadastrado
     * @return O enfermeiro cadastrado com ID gerado
     */
    Enfermeiro cadastrar(Enfermeiro enfermeiro);

    /**
     * Busca um enfermeiro pelo ID.
     *
     * @param id O ID do enfermeiro
     * @return Um Optional contendo o enfermeiro, se encontrado
     */
    Optional<Enfermeiro> buscarPorId(UUID id);

    /**
     * Lista todos os enfermeiros com paginação e filtros opcionais.
     *
     * @param paginacao Parâmetros de paginação
     * @return Lista paginada de enfermeiros
     */
    PaginatedResult<Enfermeiro> listarTodos(
            PaginatedRequestDTO paginacao
    );

    /**
     * Atualiza os dados de um enfermeiro existente.
     *
     * @param enfermeiro O enfermeiro com os dados atualizados
     * @return O enfermeiro atualizado
     */
    Enfermeiro atualizar(Enfermeiro enfermeiro);

    /**
     * Desativa um enfermeiro no sistema (exclusão lógica).
     *
     * @param id O ID do enfermeiro a ser desativado
     */
    void desativar(UUID id);


    /**
     * Reativa um enfermeiro no sistema (ativa o enfermeiro).
     *
     * @param id O ID do enfermeiro a ser reativado
     * @return O enfermeiro reativado
     */
    Enfermeiro reativar(UUID id);

    /**
     * Verifica se um enfermeiro está ativo no sistema.
     *
     * @param id O ID do enfermeiro
     * @return true se o enfermeiro estiver ativo, false caso contrário
     */
    boolean estaAtivo(UUID id);
}
