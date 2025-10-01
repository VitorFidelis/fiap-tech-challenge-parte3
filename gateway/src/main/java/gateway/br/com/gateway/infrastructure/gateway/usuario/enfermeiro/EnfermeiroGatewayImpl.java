package gateway.br.com.gateway.infrastructure.gateway.usuario.enfermeiro;

import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.application.output.usuario.enfermeiro.EnfermeiroDataSource;
import gateway.br.com.gateway.application.output.usuario.enfermeiro.EnfermeiroGateway;
import gateway.br.com.gateway.domain.model.usuario.Enfermeiro;

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
}
