package gateway.br.com.gateway.infrastructure.persistence.springdata;

import gateway.br.com.gateway.infrastructure.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, UUID> {

}
