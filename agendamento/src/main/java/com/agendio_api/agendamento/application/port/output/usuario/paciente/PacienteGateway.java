package com.agendio_api.agendamento.application.port.output.usuario.paciente;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.domain.model.usuario.Paciente;

import java.util.Optional;
import java.util.UUID;

/**
 * Porta de saída para operações relacionadas a Pacientes.
 * Segue o princípio da Inversão de Dependência (SOLID) ao definir uma abstração
 * que será implementada pela camada de infraestrutura.
 * <p>
 * Esta interface é utilizada pela camada de aplicação para interagir com
 * a camada de infraestrutura, mantendo o desacoplamento entre elas.
 */
public interface PacienteGateway {

    /**
     * Cadastra um novo paciente no sistema.
     *
     * @param paciente O paciente a ser cadastrado
     * @return O paciente cadastrado com ID gerado
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
     * @param paginacao Parâmetros de paginação (não pode ser nulo)
     * @return Lista paginada de pacientes
     * @throws IllegalArgumentException se os parâmetros de paginação forem inválidos
     */
    PaginatedResult<Paciente> listarTodos(
            PaginatedRequestDTO paginacao
    );

    /**
     * Atualiza os dados de um paciente existente.
     *
     * @param paciente O paciente com os dados atualizados
     * @return O paciente atualizado
     * @throws jakarta.persistence.EntityNotFoundException se o paciente não for encontrado
     */
    Paciente atualizar(Paciente paciente);

    /**
     * Desativa um paciente no sistema (exclusão lógica).
     *
     * @param id O ID do paciente a ser desativado
     * @throws jakarta.persistence.EntityNotFoundException se o paciente não for encontrado
     */
    void desativar(UUID id);

    /**
     * Reativa um paciente no sistema .
     *
     * @param id O ID do paciente volta a ser ativado
     */
    Paciente reativar(UUID id);

    /**
     * Verifica se um paciente está ativo no sistema.
     *
     * @param id O ID do paciente
     * @return true se o paciente estiver ativo, false caso contrário
     */
    boolean estaAtivo(UUID id);

    Optional<Paciente> buscarPorEmail(String email);

}
