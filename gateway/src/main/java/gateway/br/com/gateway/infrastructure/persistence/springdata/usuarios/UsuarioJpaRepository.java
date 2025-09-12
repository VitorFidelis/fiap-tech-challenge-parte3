package gateway.br.com.gateway.infrastructure.persistence.springdata.usuarios;

import gateway.br.com.gateway.infrastructure.persistence.entity.usuarios.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, UUID> {

}
