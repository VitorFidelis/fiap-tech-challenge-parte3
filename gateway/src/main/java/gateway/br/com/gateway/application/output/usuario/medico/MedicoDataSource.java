package gateway.br.com.gateway.application.output.usuario.medico;

import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.domain.model.usuario.Medico;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface que define as operações de acesso a dados para a entidade Médico.
 * Segue o princípio da Segregação de Interface (ISP) ao definir apenas os métodos necessários.
 * <p>
 * Esta interface é parte da camada de porta de saída da Clean Architecture,
 * fornecendo uma abstração para operações de persistência de médicos.
 */
public interface MedicoDataSource {

    /**
     * Persiste um novo médico no banco de dados.
     *
     * @param medico O médico a ser persistido
     * @return O médico persistido com ID gerado
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
     * Verifica se já existe um médico cadastrado com o CRM informado.
     *
     * @param crm O CRM a ser verificado
     * @return true se já existir um médico com o CRM, false caso contrário
     */
    boolean existePorCrm(String crm);

    /**
     * Lista todos os médicos com suporte a paginação e filtros.
     *
     * @param paginacao Parâmetros de paginação
     * @return Lista paginada de médicos
     */
    PaginatedResult<Medico> listarTodos(
            PaginatedRequestDTO paginacao
    );

    /**
     * Atualiza os dados de um médico existente.
     *
     * @param medico O médico com os dados atualizados
     * @return O médico atualizado
     */
    Medico atualizar(Medico medico);

    /**
     * Desativa um médico no sistema (exclusão lógica).
     *
     * @param id O ID do médico a ser desativado
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

}
