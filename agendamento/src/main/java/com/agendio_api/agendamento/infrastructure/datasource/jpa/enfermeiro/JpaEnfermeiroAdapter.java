package com.agendio_api.agendamento.infrastructure.datasource.jpa.enfermeiro;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.mapper.enfermeiro.IEnfermeiroMapper;
import com.agendio_api.agendamento.application.port.output.usuario.enfermeiro.EnfermeiroDataSource;
import com.agendio_api.agendamento.domain.exception.UsuarioNaoEncontradoException;
import com.agendio_api.agendamento.domain.model.usuario.Enfermeiro;
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
        JpaEnfermeiroEntity existente = repository.findById(enfermeiro.getId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Enfermeiro não encontrado"));

        existente.setNome(enfermeiro.getNome());
        existente.setEmail(enfermeiro.getEmail());
        existente.setCoren(enfermeiro.getCoren());
        existente.setAtivo(enfermeiro.isAtivo());
        existente.setAtualizadoEm(LocalDateTime.now());

        JpaEnfermeiroEntity atualizado = repository.save(existente);
        return enfermeiroMapper.toDomain(atualizado);
    }

    @Override
    public void desativar(UUID id) {
        JpaEnfermeiroEntity entity = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Enfermeiro não encontrado"));

        Enfermeiro enfermeiro = enfermeiroMapper.toDomain(entity);

        enfermeiro.desativar();
        entity.setAtivo(enfermeiro.isAtivo());
        entity.setAtualizadoEm(LocalDateTime.now());

        repository.save(entity);

    }

    @Override
    public Enfermeiro reativar(UUID id) {
        JpaEnfermeiroEntity entity = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Enfermeiro não encontrado"));

        Enfermeiro enfermeiro = enfermeiroMapper.toDomain(entity);
        enfermeiro.reativar();
        entity.setAtivo(enfermeiro.isAtivo());
        entity.setAtualizadoEm(LocalDateTime.now());
        JpaEnfermeiroEntity atualizado = repository.save(entity);
        return enfermeiroMapper.toDomain(atualizado);
    }


    @Override
    public boolean estaAtivo(UUID id) {
        JpaEnfermeiroEntity entity = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Enfermeiro não encontrado"));

        Enfermeiro enfermeiro = enfermeiroMapper.toDomain(entity);
        return enfermeiro.isAtivo();
    }


    @Override
    public Optional<Enfermeiro> buscarPorEmail(String email) {
        return repository.findByEmail(email).map(enfermeiroMapper::toDomain);
    }

}
