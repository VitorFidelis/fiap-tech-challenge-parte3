package gateway.br.com.gateway.application.output.consulta;

import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.application.dto.usuarios.UsuarioIdFiltroPaginadoRequestDTO;
import gateway.br.com.gateway.domain.model.consulta.Consulta;
import gateway.br.com.gateway.domain.model.consulta.FiltroBuscaConsulta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Porta de saída para operações relacionadas a Consultas.
 * Segue o princípio da Inversão de Dependência (SOLID) ao definir uma abstração
 * que será implementada pela camada de infraestrutura.
 */
public interface ConsultaGateway {

    /**
     * Agenda uma nova consulta.
     *
     * @param consulta Dados da consulta a ser agendada
     * @return Consulta agendada com ID gerado
     */
    Consulta agendar(Consulta consulta);

    /**
     * Busca uma consulta pelo seu ID.
     *
     * @param id ID da consulta
     * @return Optional contendo a consulta, se encontrada
     */
    Optional<Consulta> buscarPorId(UUID id);

    /**
     * Lista todas as consultas de um paciente.
     *
     * @return Lista de consultas do paciente
     */
    PaginatedResult<Consulta> listarPorPaciente(UsuarioIdFiltroPaginadoRequestDTO request);

    PaginatedResult<Consulta> listarPorEnfermeiro(UsuarioIdFiltroPaginadoRequestDTO request);

    PaginatedResult<Consulta> listarPorMedico(UsuarioIdFiltroPaginadoRequestDTO dto);


    PaginatedResult<Consulta> listarPorMedicoEPeriodo(
            FiltroBuscaConsulta filtro,
            PaginatedRequestDTO paginacao
    );

    /**
     * Verifica se já existe consulta agendada para o médico no horário.
     *
     * @param medicoId ID do médico
     * @param dataHora Data/hora da consulta
     * @return true se já houver consulta agendada, false caso contrário
     */
    boolean existeConsultaNoHorario(UUID medicoId, LocalDateTime dataHora);

    /**
     * Cancela uma consulta.
     *
     * @param id                 ID da consulta a ser cancelada
     * @param motivoCancelamento Motivo do cancelamento
     */
    void cancelar(UUID id, String motivoCancelamento);

    /**
     * Atualiza os dados de uma consulta.
     *
     * @param consulta Dados atualizados da consulta
     * @return Consulta atualizada
     */
    Consulta atualizar(Consulta consulta);

    /**
     * Lista todas as consultas em um determinado período.
     *
     * @param inicio Data/hora inicial
     * @param fim    Data/hora final
     * @return Lista de consultas no período
     */
    PaginatedResult<Consulta> listarPorPeriodo(UUID medicoId, LocalDate inicio, LocalDate fim, PaginatedRequestDTO paginacao);

    /**
     * Verifica se o paciente já possui consulta agendada no período.
     *
     * @param pacienteId ID do paciente
     * @param inicio     Data/hora inicial
     * @param fim        Data/hora final
     * @return true se já houver consulta agendada, false caso contrário
     */
    boolean pacientePossuiConsultaNoPeriodo(UUID pacienteId, LocalDateTime inicio, LocalDateTime fim);


    /**
     * Lista todas as consultas em um determinado período, com paginação.
     *
     * @param paginacao Parâmetros de paginação (página, tamanho, ordenação)
     * @return Lista paginada de todas as consultas
     */
    PaginatedResult<Consulta> listarTodas(PaginatedRequestDTO paginacao);
}
