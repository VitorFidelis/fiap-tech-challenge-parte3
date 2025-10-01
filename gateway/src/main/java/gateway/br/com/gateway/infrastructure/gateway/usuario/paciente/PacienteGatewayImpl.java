package gateway.br.com.gateway.infrastructure.gateway.usuario.paciente;

import gateway.br.com.gateway.application.output.usuario.paciente.PacienteGateway;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.application.output.usuario.paciente.PacienteDataSource;
import gateway.br.com.gateway.domain.model.usuario.Paciente;

import java.util.Optional;
import java.util.UUID;

public class PacienteGatewayImpl implements PacienteGateway {
    private final PacienteDataSource dataSource;

    public PacienteGatewayImpl(PacienteDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Paciente cadastrar(Paciente paciente) {
        return dataSource.cadastrar(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorId(UUID id) {
        return dataSource.buscarPorId(id);
    }

    @Override
    public PaginatedResult<Paciente> listarTodos(PaginatedRequestDTO paginacao) {
        return dataSource.listarTodos(paginacao);
    }

    @Override
    public Paciente atualizar(Paciente paciente) {
        return dataSource.atualizar(paciente);
    }

    @Override
    public void desativar(UUID id) {
        dataSource.desativar(id);
    }

    @Override
    public Paciente reativar(UUID id) {
        return dataSource.reativar(id);
    }

    @Override
    public boolean estaAtivo(UUID id) {
        return dataSource.estaAtivo(id);
    }
}
