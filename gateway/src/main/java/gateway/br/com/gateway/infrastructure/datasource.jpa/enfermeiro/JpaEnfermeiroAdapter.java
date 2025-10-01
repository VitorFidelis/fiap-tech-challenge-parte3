package gateway.br.com.gateway.infrastructure.datasource.jpa.enfermeiro;

import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.application.mapper.enfermeiro.IEnfermeiroMapper;
import gateway.br.com.gateway.application.output.usuario.enfermeiro.EnfermeiroDataSource;
import gateway.br.com.gateway.domain.exception.UsuarioNaoEncontradoException;
import gateway.br.com.gateway.domain.model.usuario.Enfermeiro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JpaEnfermeiroAdapter implements EnfermeiroDataSource {

    private final JpaEnfermeiroRepository repository;
    private final IEnfermeiroMapper enfermeiroMapper;

    public JpaEnfermeiroAdapter(JpaEnfermeiroRepository repository, IEnfermeiroMapper enfermeiroMapper) {
        this.repository = repository;
        this.enfermeiroMapper = enfermeiroMapper;
    }

    @Override
    public Enfermeiro cadastrar(Enfermeiro enfermeiro) {
        JpaEnfermeiroEntity enfermeiroSalvo = repository.save(enfermeiroMapper.toJpaEnfermeiroEntity(enfermeiro));
        return enfermeiroMapper.toDomain(enfermeiroSalvo);
    }

    @Override
    public Optional<Enfermeiro> buscarPorId(UUID id) {
        return repository.findByIdAndAtivoTrue(id).map(enfermeiroMapper::toDomain);
    }


    @Override
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }


    @Override
    public PaginatedResult<Enfermeiro> listarTodos(PaginatedRequestDTO paginacao) {
        Pageable pageable = PageRequest.of(
                paginacao.page(),
                paginacao.size(),
                Sort.by(paginacao.sort())
        );

        Page<JpaEnfermeiroEntity> pageResult = repository.findAllByAtivoTrue(pageable);


        List<Enfermeiro> enfermeiros = pageResult
                .getContent()
                .stream()
                .map(enfermeiroMapper::toDomain)
                .toList();

        return new PaginatedResult<>(
                enfermeiros,
                pageResult.getTotalElements(),
                pageResult.getTotalPages()
        );
    }


    @Override
    public Enfermeiro atualizar(Enfermeiro enfermeiro) {
        // Converte o domínio para entidade JPA
        JpaEnfermeiroEntity entity = enfermeiroMapper.toJpaEnfermeiroEntity(enfermeiro);

        // Garante que "atualizadoEm" seja modificado
        entity.setAtualizadoEm(LocalDateTime.now());

        JpaEnfermeiroEntity atualizado = repository.save(entity);

        // Converte de volta para domínio
        return enfermeiroMapper.toDomain(atualizado);
    }

    @Override
    public void desativar(UUID id) {
        JpaEnfermeiroEntity entity = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Enfermeiro não encontrado"));

        Enfermeiro enfermeiro = enfermeiroMapper.toDomain(entity);

        enfermeiro.desativar();

        repository.save(enfermeiroMapper.toJpaEnfermeiroEntity(enfermeiro));

    }

    @Override
    public boolean estaAtivo(UUID id) {
        JpaEnfermeiroEntity entity = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Enfermeiro não encontrado"));

        Enfermeiro enfermeiro = enfermeiroMapper.toDomain(entity);
        return enfermeiro.isAtivo();
    }

    @Override
    public Enfermeiro reativar(UUID id) {

        return repository.findById(id)
                .map(enfermeiroMapper::toDomain)
                .map(enfermeiro -> {
                    enfermeiro.reativar();
                    return repository.save(enfermeiroMapper.toJpaEnfermeiroEntity(enfermeiro));
                })
                .map(repository::save)
                .map(enfermeiroMapper::toDomain)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Enfermeiro não encontrado"));
    }

}
