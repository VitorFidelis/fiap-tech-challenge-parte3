package gateway.br.com.gateway.infrastructure.persistence.springdata;

import gateway.br.com.gateway.infrastructure.persistence.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioJpaRepository extends JpaRepository<TipoUsuarioEntity, Long> {

}
