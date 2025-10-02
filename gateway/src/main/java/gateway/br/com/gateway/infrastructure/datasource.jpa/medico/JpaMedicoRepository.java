package gateway.br.com.gateway.infrastructure.datasource.jpa.medico;

import gateway.br.com.gateway.infrastructure.persistence.entity.usuarios.JpaMedicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaMedicoRepository extends JpaRepository<JpaMedicoEntity, UUID> {
    Optional<JpaMedicoEntity> findByCrm(String crm);

    boolean existsByCrm(String crm);

    Optional<JpaMedicoEntity> findByIdAndAtivoTrue(UUID id);

    Page<JpaMedicoEntity> findByEspecialidadeIgnoreCaseAndAtivoTrue(String especialidade, Pageable pageable);

    Optional<JpaMedicoEntity> findByCrmAndAtivoTrue(String crm);

    Page<JpaMedicoEntity> findAllByAtivoTrue(Pageable pageable);
}
