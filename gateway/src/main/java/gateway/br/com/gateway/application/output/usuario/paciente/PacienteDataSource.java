package gateway.br.com.gateway.application.output.usuario.paciente;

import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.domain.model.usuario.Paciente;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface que define as operações de acesso a dados para a entidade Paciente.
 * Segue o princípio da Segregação de Interface (ISP) ao definir apenas os métodos necessários.
 * <p>
 * Esta interface é parte da camada de porta de saída da Clean Architecture,
 * fornecendo uma abstração para operações de persistência de pacientes.
 */
public interface PacienteDataSource {

    /**
     * Persiste um novo paciente no banco de dados.
     *
     * @param paciente O paciente a ser persistido
     * @return O paciente persistido com ID gerado
     * @throws IllegalArgumentException se os dados do paciente forem inválidos
     */
    Paciente cadastrar(Paciente paciente);

    /**
     * Busca um paciente pelo seu identificador único.
     *
     * @param id O ID do paciente
     * @return Um Optional contendo o paciente, se encontrado
     */
    Optional<Paciente> buscarPorId(UUID id);

    /**
     * Lista todos os pacientes com suporte a paginação e filtros.
     *
     * @param paginacao Parâmetros de paginação
     * @return Lista paginada de pacientes
     */
    PaginatedResult<Paciente> listarTodos(
            PaginatedRequestDTO paginacao
    );

    /**
     * Atualiza os dados de um paciente existente.
     *
     * @param paciente O paciente com os dados atualizados
     * @return O paciente atualizado
     */
    Paciente atualizar(Paciente paciente);

    /**
     * Desativa um paciente no sistema (exclusão lógica).
     *
     * @param id O ID do paciente a ser desativado
     */
    void desativar(UUID id);


    /**
     * Reativa um paciente previamente desativado.
     *
     * @param id O ID do paciente a ser reativado
     * @return O paciente reativado
     */
    Paciente reativar(UUID id);

    /**
     * Verifica se um paciente está ativo no sistema.
     *
     * @param id O ID do paciente
     * @return true se o paciente estiver ativo, false caso contrário
     */
    boolean estaAtivo(UUID id);

}
