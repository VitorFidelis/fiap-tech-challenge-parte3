package gateway.br.com.gateway.infrastructure.datasource.jpa.enfermeiro;

import gateway.br.com.gateway.infrastructure.persistence.entity.usuarios.JpaEnfermeiroEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaEnfermeiroRepository extends JpaRepository<JpaEnfermeiroEntity, UUID> {

    boolean existsByEmail(String email);

    Page<JpaEnfermeiroEntity> findAllByAtivoTrue(Pageable pageable);

    Optional<JpaEnfermeiroEntity> findByIdAndAtivoTrue(UUID id);
}
