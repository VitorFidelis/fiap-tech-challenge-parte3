package gateway.br.com.gateway.application.output.consulta;

import gateway.br.com.gateway.application.dto.consulta.ConsultaFiltroRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.application.dto.usuarios.UsuarioIdFiltroPaginadoRequestDTO;
import gateway.br.com.gateway.domain.model.consulta.Consulta;
import gateway.br.com.gateway.domain.model.consulta.FiltroBuscaConsulta;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


/**
 * Interface que define as operações de acesso a dados para a entidade Consulta.
 * Segue o princípio da Segregação de Interface (ISP) ao definir apenas os métodos necessários.
 */
public interface ConsultaDataSource {

    /**
     * Salva uma nova consulta no banco de dados.
     *
     * @param consulta A consulta a ser salva
     * @return A consulta salva com ID gerado
     */
    Consulta agendar(Consulta consulta);

    /**
     * Busca uma consulta pelo ID.
     *
     * @param id O ID da consulta
     * @return Um Optional contendo a consulta, se encontrada
     */
    Optional<Consulta> buscarPorId(UUID id);

    /**
     * Verifica se existe uma consulta para o médico no horário especificado.
     *
     * @param medicoId O ID do médico
     * @param dataHora A data e hora da consulta
     * @return true se existir uma consulta no horário, false caso contrário
     */
    boolean existeConsultaNoHorario(UUID medicoId, LocalDateTime dataHora);


    PaginatedResult<Consulta> listarPorMedicoEPeriodo(
            FiltroBuscaConsulta filtro,
            PaginatedRequestDTO paginacao
    );

    /**
     * Lista consultas dentro de um período com paginação.
     *
     * @param filtro    Filtros para a busca
     * @param paginacao Parâmetros de paginação
     * @return Lista paginada de consultas
     */
    PaginatedResult<Consulta> listarPorPeriodo(
            ConsultaFiltroRequestDTO filtro,
            PaginatedRequestDTO paginacao
    );

    /**
     * Atualiza uma consulta existente.
     *
     * @param consulta A consulta com os dados atualizados
     * @return A consulta atualizada
     */
    Consulta atualizar(Consulta consulta);

    /**
     * Cancela uma consulta.
     *
     * @param id                 O ID da consulta a ser cancelada
     * @param motivoCancelamento O motivo do cancelamento
     */
    void cancelar(UUID id, String motivoCancelamento);

    /**
     * Verifica se o paciente possui consulta agendada no período especificado.
     *
     * @param pacienteId O ID do paciente
     * @param inicio     Data/hora inicial do período
     * @param fim        Data/hora final do período
     * @return true se o paciente tiver consulta no período, false caso contrário
     */
    boolean pacientePossuiConsultaNoHorario(UUID pacienteId, LocalDateTime inicio, LocalDateTime fim);

    /**
     * Lista todas as consultas de um paciente.
     *
     * @param request Os dados do paciente para filtrar as consultas e parâmetros de paginação
     * @return Uma lista paginada de consultas do paciente
     */
    PaginatedResult<Consulta> listarPorPaciente(UsuarioIdFiltroPaginadoRequestDTO request);

    /**
     * Lista todas as consultas de um enfermeiro.
     *
     * @param request Os dados do enfermeiro para filtrar as consultas
     * @return Uma lista paginada de consultas do enfermeiro
     */
    PaginatedResult<Consulta> listarPorEnfermeiro(UsuarioIdFiltroPaginadoRequestDTO request);


    PaginatedResult<Consulta> listarPorMedico(UsuarioIdFiltroPaginadoRequestDTO dto);

    PaginatedResult<Consulta> listarTodas(PaginatedRequestDTO paginacao);
}
