package gateway.br.com.gateway.infrastructure.datasource.jpa.consulta;

import gateway.br.com.gateway.application.dto.consulta.ConsultaFiltroRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedRequestDTO;
import gateway.br.com.gateway.application.dto.paginated.PaginatedResult;
import gateway.br.com.gateway.application.dto.usuarios.UsuarioIdFiltroPaginadoRequestDTO;
import gateway.br.com.gateway.application.mapper.consulta.IConsultaMapper;
import gateway.br.com.gateway.application.output.consulta.ConsultaDataSource;
import gateway.br.com.gateway.domain.exception.ConsultaNaoEncontradaException;
import gateway.br.com.gateway.domain.model.consulta.Consulta;
import gateway.br.com.gateway.domain.model.consulta.FiltroBuscaConsulta;
import gateway.br.com.gateway.domain.model.consulta.StatusConsulta;
import gateway.br.com.gateway.infrastructure.persistence.entity.consulta.JpaConsultaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class JpaConsultaAdapter implements ConsultaDataSource {

    private final JpaConsultaRepository consultaRepository;
    private final IConsultaMapper consultaMapper;

    @Override
    @Transactional
    public Consulta agendar(Consulta consulta) {
        var jpaEntity = consultaMapper.toJpaConsultaEntity(consulta);
        var savedEntity = consultaRepository.save(jpaEntity);
        return consultaMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Consulta> buscarPorId(UUID id) {
        return consultaRepository.findByIdAndAtivoTrue(id)
                .map(consultaMapper::toDomain);
    }

    @Override
    public boolean existeConsultaNoHorario(UUID medicoId, LocalDateTime dataHora) {
        return consultaRepository.existsByMedicoIdAndHorarioSolicitadoAndStatus(medicoId, dataHora, StatusConsulta.AGENDADA);
    }

    @Override
    public PaginatedResult<Consulta> listarPorMedicoEPeriodo(FiltroBuscaConsulta filtro, PaginatedRequestDTO paginacao) {
        Pageable pageable = PageRequest.of(
                paginacao.page(),
                paginacao.size(),
                Sort.by(paginacao.sort())
        );

        UUID medicoId = filtro.medicoId();
        LocalDate inicio = filtro.periodo().inicio();
        LocalDate fim = filtro.periodo().fim();


        LocalDateTime inicioDateTime = inicio.atStartOfDay();
        LocalDateTime fimDateTime = fim.atTime(23, 59, 59);

        Page<JpaConsultaEntity> pageResult =
                consultaRepository.findByMedicoIdAndHorarioSolicitadoBetweenAndStatus(medicoId, inicioDateTime, fimDateTime, StatusConsulta.AGENDADA, pageable);

        List<Consulta> consultas = pageResult
                .getContent()
                .stream()
                .map(consultaMapper::toDomain)
                .toList();

        return new PaginatedResult<>(
                consultas,
                pageResult.getTotalElements(),
                pageResult.getTotalPages()
        );
    }

    @Override
    public PaginatedResult<Consulta> listarPorPeriodo(ConsultaFiltroRequestDTO filtro, PaginatedRequestDTO paginacao) {
        return null;
    }

    @Override
    @Transactional
    public Consulta atualizar(Consulta consulta) {
        JpaConsultaEntity consultaExistente = consultaRepository
                .findByIdAndAtivoTrue(consulta.getId())
                .orElseThrow(
                        () -> new ConsultaNaoEncontradaException("consulta não encontrada com id " + consulta.getId()));

        consultaExistente.setHorarioSolicitado(consulta.getHorarioSolicitado());
        consultaExistente.setObservacoes(consulta.getObservacoes());
        consultaExistente.setStatus(consulta.getStatus());
        consultaExistente.setAtualizadoEm(LocalDateTime.now());
        JpaConsultaEntity consultaSalva = consultaRepository.save(consultaExistente);

        return consultaMapper.toDomain(consultaSalva);
    }

    @Override
    @Transactional
    public void cancelar(UUID id, String motivoCancelamento) {
        JpaConsultaEntity consultaEntity = consultaRepository.findById(id)
                .orElseThrow(() -> new ConsultaNaoEncontradaException("consulta não encontrada com id " + id));

        Consulta consulta = consultaMapper.toDomain(consultaEntity);
        Consulta consultaCancelada = consulta.cancelar(motivoCancelamento);

        consultaEntity.setStatus(consultaCancelada.getStatus());
        consultaEntity.setObservacoes(consultaCancelada.getObservacoes());
        consultaEntity.setAtualizadoEm(consultaCancelada.getAtualizadoEm());
        consultaEntity.setAtivo(consultaCancelada.isAtivo());
        consultaRepository.save(consultaEntity);
    }

    @Override
    public boolean pacientePossuiConsultaNoHorario(UUID pacienteId, LocalDateTime inicio, LocalDateTime fim) {
        return consultaRepository.existsByPacienteIdAndHorarioSolicitadoBetweenAndStatus(pacienteId, inicio, fim, StatusConsulta.AGENDADA);
    }

    @Override
    public PaginatedResult<Consulta> listarPorPaciente(UsuarioIdFiltroPaginadoRequestDTO request) {
        Pageable pageable = PageRequest.of(
                request.paginacao().page(),
                request.paginacao().size(),
                Sort.by(request.paginacao().sort())
        );

        UUID pacienteId = request.usuarioId();

        Page<JpaConsultaEntity> page = consultaRepository.findByPacienteIdAndAtivoTrue(pacienteId, pageable);

        List<Consulta> consultas = page.getContent().stream()
                .map(consultaMapper::toDomain)
                .toList();

        return new PaginatedResult<>(
                consultas,
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    @Override
    public PaginatedResult<Consulta> listarPorEnfermeiro(UsuarioIdFiltroPaginadoRequestDTO request) {
        Pageable pageable = PageRequest.of(request.paginacao().page(), request.paginacao().size(), Sort.by(request.paginacao().sort()));
        Page<JpaConsultaEntity> page = consultaRepository.findByEnfermeiroIdAndAtivoTrue(request.usuarioId(), pageable);
        List<Consulta> consultas = page.getContent().stream().map(consultaMapper::toDomain).toList();
        return new PaginatedResult<>(consultas, page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public PaginatedResult<Consulta> listarPorMedico(UsuarioIdFiltroPaginadoRequestDTO filtro) {
        Pageable pageable = PageRequest.of(filtro.paginacao().page(), filtro.paginacao().size(), Sort.by(filtro.paginacao().sort()));

        Page<JpaConsultaEntity> page = consultaRepository.findByMedicoIdAndAtivoTrue(filtro.usuarioId(), pageable);
        List<Consulta> consultas = page.getContent().stream().map(consultaMapper::toDomain).toList();
        return new PaginatedResult<>(consultas, page.getTotalElements(), page.getTotalPages());
    }


    @Override
    public PaginatedResult<Consulta> listarTodas(PaginatedRequestDTO paginacao) {
        Pageable pageable = PageRequest.of(paginacao.page(), paginacao.size(), Sort.by(paginacao.sort()));

        Page<JpaConsultaEntity> pageResult = consultaRepository.findAllByAtivoTrue(pageable);

        List<Consulta> consultas = pageResult
                .getContent()
                .stream()
                .map(consultaMapper::toDomain)
                .toList();

        return new PaginatedResult<>(consultas, pageResult.getTotalElements(), pageResult.getTotalPages());
    }


}
