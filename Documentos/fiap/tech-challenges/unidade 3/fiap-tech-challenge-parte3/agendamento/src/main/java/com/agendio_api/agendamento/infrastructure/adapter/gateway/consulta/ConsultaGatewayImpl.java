package com.agendio_api.agendamento.infrastructure.adapter.gateway.consulta;

import com.agendio_api.agendamento.application.port.dto.consulta.ConsultaFiltroRequestDTO;
import com.agendio_api.agendamento.application.port.dto.consulta.graphql.ListarConsultaGraphqlDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.dto.usuario.UsuarioIdFiltroPaginadoRequestDTO;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaDataSource;
import com.agendio_api.agendamento.application.port.output.consulta.ConsultaGateway;
import com.agendio_api.agendamento.domain.model.consulta.Consulta;
import com.agendio_api.agendamento.domain.model.consulta.FiltroConsulta;
import com.agendio_api.agendamento.infrastructure.exception.GatewayException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class ConsultaGatewayImpl implements ConsultaGateway {
    private final ConsultaDataSource dataSource;

    public ConsultaGatewayImpl(ConsultaDataSource dataSource) {
        this.dataSource = Objects.requireNonNull(dataSource, "DataSource não pode ser nulo");
    }

    @Override
    public Consulta agendar(Consulta consulta) {
        return dataSource.agendar(consulta);
    }

    @Override
    public Optional<Consulta> buscarPorId(UUID id) {
        return dataSource.buscarPorId(id);
    }

    @Override
    public PaginatedResult<Consulta> listarPorPaciente(UsuarioIdFiltroPaginadoRequestDTO request) {
        return dataSource.listarPorPaciente(request);
    }

    @Override
    public PaginatedResult<Consulta> listarPorEnfermeiro(UsuarioIdFiltroPaginadoRequestDTO request) {
        return dataSource.listarPorEnfermeiro(request);
    }

    @Override
    public PaginatedResult<Consulta> listarPorMedico(UsuarioIdFiltroPaginadoRequestDTO dto) {
        return dataSource.listarPorMedico(dto);
    }

    @Override
    public PaginatedResult<Consulta> listarPorMedicoEPeriodo(FiltroConsulta filtro, PaginatedRequestDTO paginacao) {
        return dataSource.listarPorMedicoEPeriodo(filtro, paginacao);
    }

//    @Override
//    public PaginatedResult<Consulta> listarPorMedicoEPeriodo(ConsultaFiltroRequestDTO requestDTO, PaginatedRequestDTO paginacao) {
//        return dataSource.listarPorMedicoEPeriodo(requestDTO, paginacao);
//    }

    @Override
    public boolean existeConsultaNoHorario(UUID medicoId, LocalDateTime dataHora) {
        return dataSource.existeConsultaNoHorario(medicoId, dataHora);
    }

    @Override
    public void cancelar(UUID id, String motivoCancelamento) {
        dataSource.cancelar(id, motivoCancelamento);
    }

    @Override
    public Consulta atualizar(Consulta consulta) {
        return dataSource.atualizar(consulta);
    }

    @Override
    public PaginatedResult<Consulta> listarPorPeriodo(UUID medicoId, LocalDateTime inicio, LocalDateTime fim, PaginatedRequestDTO paginacao) {

        return dataSource.listarPorPeriodo(new ConsultaFiltroRequestDTO(medicoId, inicio, fim), paginacao);
    }

    @Override
    public boolean pacientePossuiConsultaNoPeriodo(
            UUID pacienteId,
            LocalDateTime inicio,
            LocalDateTime fim) {

        Objects.requireNonNull(pacienteId, "ID do paciente não pode ser nulo");
        Objects.requireNonNull(inicio, "Data início não pode ser nula");
        Objects.requireNonNull(fim, "Data fim não pode ser nula");

        if (inicio.isAfter(fim)) {
            throw new IllegalArgumentException("Data início não pode ser posterior à data fim");
        }

        try {
            return dataSource.pacientePossuiConsultaNoHorario(pacienteId, inicio, fim);
        } catch (Exception e) {
            throw new GatewayException("Erro ao verificar consultas do paciente");
        }
    }

    @Override
    public PaginatedResult<Consulta> listarTodas(PaginatedRequestDTO paginacao) {
        return dataSource.listarTodas(paginacao);
    }

    @Override
    public List<Consulta> listagemConsultaGraphql(ListarConsultaGraphqlDTO filtros) {
        return dataSource.listarComFiltrosFlexiveis(filtros);
    }
}
