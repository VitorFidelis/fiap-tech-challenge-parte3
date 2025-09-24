package gateway.br.com.gateway.infrastructure.persistence.springdata.usuarios;

import gateway.br.com.gateway.infrastructure.persistence.entity.usuarios.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@EnableJpaRepositories
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, UUID> {
    UsuarioEntity findByEmail(String email);
}
