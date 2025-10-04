package com.agendio_api.agendamento.application.port.output.usuario.medico;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.domain.model.usuario.Medico;

import java.util.Optional;
import java.util.UUID;

/**
 * Porta de saída para operações relacionadas a Médicos.
 * Segue o princípio da Inversão de Dependência (SOLID) ao definir uma abstração
 * que será implementada pela camada de infraestrutura.
 * <p>
 * Esta interface é utilizada pela camada de aplicação para interagir com
 * a camada de infraestrutura, mantendo o desacoplamento entre elas.
 */
public interface MedicoGateway {

    /**
     * Cadastra um novo médico no sistema.
     *
     * @param medico O médico a ser cadastrado
     * @return O médico cadastrado com ID gerado
     * @throws IllegalArgumentException se os dados do médico forem inválidos
     */
    Medico cadastrar(Medico medico);

    /**
     * Busca um médico pelo seu identificador único.
     *
     * @param id O ID do médico
     * @return Um Optional contendo o médico, se encontrado
     */
    Optional<Medico> buscarPorId(UUID id);

    /**
     * Busca um médico pelo seu CRM.
     *
     * @param crm O CRM do médico no formato 'CRM/UF 123456'
     * @return Um Optional contendo o médico, se encontrado
     */
    Optional<Medico> buscarPorCrm(String crm);

    /**
     * Lista todos os médicos com suporte a paginação e filtros.
     *
     * @param paginacao Parâmetros de paginação (não pode ser nulo)
     * @return Lista paginada de médicos
     * @throws IllegalArgumentException se os parâmetros de paginação forem inválidos
     */
    PaginatedResult<Medico> listarTodos(
            PaginatedRequestDTO paginacao
    );

    /**
     * Atualiza os dados de um médico existente.
     *
     * @param medico O médico com os dados atualizados
     * @return O médico atualizado
     * @throws jakarta.persistence.EntityNotFoundException se o médico não for encontrado
     */
    Medico atualizar(Medico medico);

    /**
     * Desativa um médico no sistema (exclusão lógica).
     *
     * @param id O ID do médico a ser desativado
     * @throws jakarta.persistence.EntityNotFoundException se o médico não for encontrado
     */
    void desativar(UUID id);


    /**
     * Reativa um médico previamente desativado.
     *
     * @param id O ID do médico a ser reativado
     * @return O médico reativado
     */
    Medico reativar(UUID id);

    /**
     * Verifica se um médico está ativo no sistema.
     *
     * @param id O ID do médico
     * @return true se o médico estiver ativo, false caso contrário
     */
    boolean estaAtivo(UUID id);


    /**
     * Verifica se já existe um médico cadastrado com o CRM informado.
     *
     * @param crm O CRM a ser verificado
     * @return true se já existir um médico com o CRM, false caso contrário
     */
    boolean existePorCrm(String crm);


    /**
     * Busca médicos por especialidade.
     *
     * @param especialidade A especialidade dos médicos a serem buscados
     * @param paginacao     Parâmetros de paginação
     * @return Lista paginada de médicos da especialidade
     */
    PaginatedResult<Medico> buscarPorEspecialidade(
            String especialidade,
            PaginatedRequestDTO paginacao
    );

    /**
     * Verifica se um médico está disponível em um determinado horário.
     *
     * @param medicoId O ID do médico
     * @param dataHora A data e hora para verificação
     * @return true se o médico estiver disponível, false caso contrário
     */
    boolean verificarDisponibilidade(UUID medicoId, java.time.LocalDateTime dataHora);

}
