package com.agendio_api.agendamento.infrastructure.adapter.gateway.usuario.medico;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.output.usuario.medico.MedicoDataSource;
import com.agendio_api.agendamento.application.port.output.usuario.medico.MedicoGateway;
import com.agendio_api.agendamento.domain.model.usuario.Medico;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class MedicoGatewayImpl implements MedicoGateway {
    private final MedicoDataSource dataSource;

    public MedicoGatewayImpl(MedicoDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Medico cadastrar(Medico medico) {
        return dataSource.cadastrar(medico);
    }

    @Override
    public Optional<Medico> buscarPorId(UUID id) {
        return dataSource.buscarPorId(id);
    }

    @Override
    public Optional<Medico> buscarPorCrm(String crm) {
        return dataSource.buscarPorCrm(crm);
    }

    @Override
    public PaginatedResult<Medico> listarTodos(PaginatedRequestDTO paginacao) {
        return dataSource.listarTodos(paginacao);
    }

    @Override
    public Medico atualizar(Medico medico) {
        return dataSource.atualizar(medico);
    }

    @Override
    public void desativar(UUID id) {
        dataSource.desativar(id);
    }

    @Override
    public Medico reativar(UUID id) {
        return dataSource.reativar(id);
    }

    @Override
    public boolean estaAtivo(UUID id) {
        return dataSource.estaAtivo(id);
    }

    @Override
    public boolean existePorCrm(String crm) {
        return dataSource.existePorCrm(crm);
    }

    @Override
    public PaginatedResult<Medico> buscarPorEspecialidade(String especialidade, PaginatedRequestDTO paginacao) {
        return dataSource.buscarPorEspecialidade(especialidade, paginacao);
    }

    @Override
    public boolean verificarDisponibilidade(UUID medicoId, LocalDateTime dataHora) {
        return false;
    }

    @Override
    public Optional<Medico> buscarPorEmail(String email) {
        return dataSource.buscarPorEmail(email);
    }
}
