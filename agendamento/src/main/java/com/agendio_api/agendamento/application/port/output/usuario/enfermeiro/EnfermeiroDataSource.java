package com.agendio_api.agendamento.application.port.output.usuario.enfermeiro;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.domain.model.usuario.Enfermeiro;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface que define as operações de acesso a dados para a entidade Enfermeiro.
 * Segue o princípio da Segregação de Interface (ISP) ao definir apenas os métodos necessários.
 */
public interface EnfermeiroDataSource {


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
     * Verifica se já existe um enfermeiro cadastrado com o e-mail informado.
     *
     * @param email O e-mail a ser verificado
     * @return true se já existir um enfermeiro com o e-mail, false caso contrário
     */
    boolean existePorEmail(String email);

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
     * Exclui um enfermeiro pelo ID.
     *
     * @param id O ID do enfermeiro a ser excluído
     */
    void desativar(UUID id);

    /**
     * Verifica se um enfermeiro está ativo no sistema.
     *
     * @param id O ID do enfermeiro
     * @return true se o enfermeiro estiver ativo, false caso contrário
     */
    boolean estaAtivo(UUID id);


    /**
     * Reativa um enfermeiro previamente desativado.
     *
     * @param id O ID do enfermeiro a ser reativado
     * @return O enfermeiro reativado
     */
    Enfermeiro reativar(UUID id);
}
