package gateway.br.com.gateway.domain.repository;

import gateway.br.com.gateway.domain.model.TipoUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipoUsuarioRepository {
    TipoUsuario save(TipoUsuario tipoUsuario);
    TipoUsuario findById(Long id);
    TipoUsuario update(Long id);
    TipoUsuario dectivate(Long id);
    TipoUsuario reactivate(Long id);
    Page<TipoUsuario> findAll(Pageable pageable);
}
