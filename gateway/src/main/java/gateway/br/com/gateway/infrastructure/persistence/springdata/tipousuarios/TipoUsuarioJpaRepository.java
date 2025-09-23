package gateway.br.com.gateway.infrastructure.persistence.springdata.tipousuarios;

import gateway.br.com.gateway.infrastructure.persistence.entity.tipousuarios.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioJpaRepository extends JpaRepository<TipoUsuarioEntity, Long> {
    TipoUsuarioEntity findByNomeIgnoreCase(String nome);
}
