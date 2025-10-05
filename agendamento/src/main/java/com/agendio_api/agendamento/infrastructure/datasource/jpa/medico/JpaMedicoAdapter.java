package com.agendio_api.agendamento.infrastructure.datasource.jpa.medico;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.mapper.medico.IMedicoMapper;
import com.agendio_api.agendamento.application.port.output.usuario.medico.MedicoDataSource;
import com.agendio_api.agendamento.domain.exception.UsuarioNaoEncontradoException;
import com.agendio_api.agendamento.domain.model.usuario.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JpaMedicoAdapter implements MedicoDataSource {
    private final JpaMedicoRepository repository;
    private final IMedicoMapper medicoMapper;

    public JpaMedicoAdapter(JpaMedicoRepository repository, IMedicoMapper medicoMapper) {
        this.repository = repository;
        this.medicoMapper = medicoMapper;
    }

    @Override
    public Medico cadastrar(Medico medico) {
        JpaMedicoEntity medicoSalvo = repository.save(medicoMapper.toJpaMedicoEntity(medico));
        return medicoMapper.toDomainFromJPA(medicoSalvo);
    }

    @Override
    public Optional<Medico> buscarPorId(UUID id) {
        return repository.findByIdAndAtivoTrue(id).map(medicoMapper::toDomainFromJPA);
    }

    @Override
    public Optional<Medico> buscarPorCrm(String crm) {
        return repository.findByCrmAndAtivoTrue(crm).map(medicoMapper::toDomainFromJPA);
    }

    @Override
    public boolean existePorCrm(String crm) {
        return repository.existsByCrm(crm);
    }

    @Override
    public PaginatedResult<Medico> listarTodos(PaginatedRequestDTO paginacao) {
        Pageable pageable = PageRequest.of(
                paginacao.page(),
                paginacao.size(),
                Sort.by(paginacao.sort())
        );

        Page<JpaMedicoEntity> pageResult = repository.findAllByAtivoTrue(pageable);

        List<Medico> medicos = pageResult
                .getContent()
                .stream()
                .map(medicoMapper::toDomainFromJPA)
                .toList();

        return new PaginatedResult<>(
                medicos,
                pageResult.getTotalElements(),
                pageResult.getTotalPages()
        );
    }

    @Override
    public Medico atualizar(Medico medico) {
        JpaMedicoEntity entity = medicoMapper.toJpaMedicoEntity(medico);
        entity.setAtualizadoEm(LocalDateTime.now());
        JpaMedicoEntity atualizado = repository.save(entity);
        return medicoMapper.toDomainFromJPA(atualizado);
    }

    @Override
    public void desativar(UUID id) {
        JpaMedicoEntity entity = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Medico nao encontrado"));
        Medico medico = medicoMapper.toDomainFromJPA(entity);
        medico.desativar();
        entity = medicoMapper.toJpaMedicoEntity(medico);
        repository.save(entity);
    }

    @Override
    public Medico reativar(UUID id) {
        return repository.findById(id)
                .map(medicoMapper::toDomainFromJPA)
                .map(medico -> {
                    medico.reativar();
                    return medicoMapper.toJpaMedicoEntity(medico);
                })
                .map(repository::save)
                .map(medicoMapper::toDomainFromJPA)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Medico nao encontrado"));
    }

    @Override
    public boolean estaAtivo(UUID id) {
        JpaMedicoEntity entity = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Medico nao encontrado"));
        Medico medico = medicoMapper.toDomainFromJPA(entity);
        return medico.isAtivo();
    }

    @Override
    public PaginatedResult<Medico> buscarPorEspecialidade(String especialidade, PaginatedRequestDTO paginacao) {
        Pageable pageable = PageRequest.of(
                paginacao.page(),
                paginacao.size(),
                Sort.by(paginacao.sort())
        );
        Page<JpaMedicoEntity> pageResult = repository.findByEspecialidadeIgnoreCaseAndAtivoTrue(especialidade, pageable);
        List<Medico> medicos = pageResult
                .getContent().stream()
                .map(medicoMapper::toDomainFromJPA)
                .toList();
        return new PaginatedResult<>(
                medicos,
                pageResult.getTotalElements(),
                pageResult.getTotalPages()
        );
    }

    @Override
    public Optional<Medico> buscarPorEmail(String email) {
        return repository.findByEmail(email).map(medicoMapper::toDomainFromJPA);
    }
}
