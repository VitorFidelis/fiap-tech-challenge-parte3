package com.agendio_api.agendamento.infrastructure.datasource.jpa.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaPacienteRepository extends JpaRepository<JpaPacienteEntity, UUID> {
    Page<JpaPacienteEntity> findAllByAtivoTrue(Pageable pageable);

    Optional<JpaPacienteEntity> findByIdAndAtivoTrue(UUID id);
}
