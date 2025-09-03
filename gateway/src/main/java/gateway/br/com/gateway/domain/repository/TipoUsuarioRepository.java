package gateway.br.com.gateway.domain.repository;

import gateway.br.com.gateway.domain.model.TipoUsuario;
import org.springframework.data.domain.Page;

public interface TipoUsuarioRepository {
    TipoUsuario save(TipoUsuario tipoUsuario);
    TipoUsuario findById(Long id);
    TipoUsuario update(Long id);
    Boolean dectivate(Long id);
    Boolean reactivate(Long id);
    Page<TipoUsuario> findAll();
}
