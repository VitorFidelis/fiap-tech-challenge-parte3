package com.agendio_api.agendamento.infrastructure.datasource.jpa.paciente;

import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedRequestDTO;
import com.agendio_api.agendamento.application.port.dto.paginated.PaginatedResult;
import com.agendio_api.agendamento.application.port.mapper.paciente.IPacienteMapper;
import com.agendio_api.agendamento.application.port.output.usuario.paciente.PacienteDataSource;
import com.agendio_api.agendamento.domain.exception.UsuarioNaoEncontradoException;
import com.agendio_api.agendamento.domain.model.usuario.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JpaPacienteAdapter implements PacienteDataSource {
    private final JpaPacienteRepository repository;
    private final IPacienteMapper pacienteMapper;

    public JpaPacienteAdapter(JpaPacienteRepository repository, IPacienteMapper pacienteMapper) {
        this.repository = repository;
        this.pacienteMapper = pacienteMapper;
    }

    @Override
    public Paciente cadastrar(Paciente paciente) {
        JpaPacienteEntity pacienteSalvo = repository.save(pacienteMapper.toJpaPacienteEntity(paciente));
        return pacienteMapper.toDomain(pacienteSalvo);
    }

    @Override
    public Optional<Paciente> buscarPorId(UUID id) {
        return repository.findByIdAndAtivoTrue(id).map(pacienteMapper::toDomain);
    }

    @Override
    public PaginatedResult<Paciente> listarTodos(PaginatedRequestDTO paginacao) {
        Pageable pageable = PageRequest.of(paginacao.page(), paginacao.size(), Sort.by(paginacao.sort()));
        Page<JpaPacienteEntity> pageResult = repository.findAllByAtivoTrue(pageable);

        List<Paciente> pacientes = pageResult
                .getContent()
                .stream()
                .map(pacienteMapper::toDomain)
                .toList();

        return new PaginatedResult<>(pacientes, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    public Paciente atualizar(Paciente paciente) {
        JpaPacienteEntity entity = pacienteMapper.toJpaPacienteEntity(paciente);
        entity.setAtualizadoEm(LocalDateTime.now());
        JpaPacienteEntity atualizado = repository.save(entity);
        return pacienteMapper.toDomain(atualizado);
    }

    @Override
    public void desativar(UUID id) {
        JpaPacienteEntity entity = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Paciente nao encontrado"));
        Paciente paciente = pacienteMapper.toDomain(entity);
        paciente.desativar();
        repository.save(pacienteMapper.toJpaPacienteEntity(paciente));
    }

    @Override
    public Paciente reativar(UUID id) {
        return repository.findById(id)
                .map(pacienteMapper::toDomain)
                .map(paciente -> {
                    paciente.reativar();
                    return pacienteMapper.toJpaPacienteEntity(paciente);
                })
                .map(repository::save)
                .map(pacienteMapper::toDomain)
                .orElseThrow(() ->
                        new UsuarioNaoEncontradoException("Paciente nao encontrado"));

    }

    @Override
    public boolean estaAtivo(UUID id) {
        JpaPacienteEntity entity = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Paciente nao encontrado"));
        Paciente paciente = pacienteMapper.toDomain(entity);
        return paciente.isAtivo();
    }
}
