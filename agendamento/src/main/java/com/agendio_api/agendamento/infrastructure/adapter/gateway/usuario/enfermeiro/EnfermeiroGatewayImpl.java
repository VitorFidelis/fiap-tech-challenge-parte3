package com.agendio_api.agendamento.infrastructure.adapter.gateway.usuario.enfermeiro;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.output.usuario.enfermeiro.EnfermeiroDataSource;
import com.agendio_api.agendamento.application.port.output.usuario.enfermeiro.EnfermeiroGateway;
import com.agendio_api.agendamento.domain.model.usuario.Enfermeiro;

import java.util.Optional;
import java.util.UUID;

public class EnfermeiroGatewayImpl implements EnfermeiroGateway {
    private final EnfermeiroDataSource enfermeiroDataSource;

    public EnfermeiroGatewayImpl(EnfermeiroDataSource enfermeiroDataSource) {
        this.enfermeiroDataSource = enfermeiroDataSource;
    }

    @Override
    public Enfermeiro cadastrar(Enfermeiro enfermeiro) {
        return enfermeiroDataSource.cadastrar(enfermeiro);
    }

    @Override
    public Optional<Enfermeiro> buscarPorId(UUID id) {
        return enfermeiroDataSource.buscarPorId(id);
    }

    @Override
    public PaginatedResult<Enfermeiro> listarTodos(PaginatedRequestDTO paginacao) {
        return enfermeiroDataSource.listarTodos(paginacao);
    }

    @Override
    public Enfermeiro atualizar(Enfermeiro enfermeiro) {
        return enfermeiroDataSource.atualizar(enfermeiro);
    }

    @Override
    public void desativar(UUID id) {
        enfermeiroDataSource.desativar(id);
    }

    @Override
    public Enfermeiro reativar(UUID id) {
        return enfermeiroDataSource.reativar(id);
    }

    @Override
    public boolean estaAtivo(UUID id) {
        return enfermeiroDataSource.estaAtivo(id);
    }

    @Override
    public Optional<Enfermeiro> buscarPorEmail(String email) {
        return enfermeiroDataSource.buscarPorEmail(email);
    }
}
