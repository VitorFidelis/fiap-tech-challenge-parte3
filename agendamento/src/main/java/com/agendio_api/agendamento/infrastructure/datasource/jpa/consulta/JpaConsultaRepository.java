package com.agendio_api.agendamento.infrastructure.datasource.jpa.consulta;

import com.agendio_api.agendamento.domain.model.consulta.StatusConsulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaConsultaRepository extends JpaRepository<JpaConsultaEntity, UUID>, JpaConsultaRepositoryCustom  {

    boolean existsByMedicoIdAndHorarioSolicitadoAndStatus(UUID medicoId, LocalDateTime dataHora, StatusConsulta status);

    boolean existsByPacienteIdAndHorarioSolicitadoBetweenAndStatus(UUID pacienteId, LocalDateTime inicio, LocalDateTime fim, StatusConsulta status);

    Page<JpaConsultaEntity> findByPacienteIdAndAtivoTrue(UUID pacienteId, Pageable pageable);

    Page<JpaConsultaEntity> findByMedicoIdAndHorarioSolicitadoBetweenAndStatus(UUID medicoId, LocalDateTime inicio, LocalDateTime fim, StatusConsulta status, Pageable pageable);

    Page<JpaConsultaEntity> findByMedicoIdAndAtivoTrue(UUID medicoId, Pageable pageable);

    Optional<JpaConsultaEntity> findByIdAndAtivoTrue(UUID id);

    Page<JpaConsultaEntity> findByEnfermeiroIdAndAtivoTrue(UUID uuid, Pageable pageable);

    Page<JpaConsultaEntity> findAllByAtivoTrue(Pageable pageable);
}
