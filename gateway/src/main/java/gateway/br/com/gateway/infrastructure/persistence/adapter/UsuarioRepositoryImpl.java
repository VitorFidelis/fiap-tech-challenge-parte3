package gateway.br.com.gateway.infrastructure.persistence.adapter;

import gateway.br.com.gateway.domain.model.Usuario;
import gateway.br.com.gateway.domain.repository.UsuarioRepository;
import gateway.br.com.gateway.infrastructure.persistence.mapper.UsuarioMapper;
import gateway.br.com.gateway.infrastructure.persistence.springdata.UsuarioJpaRepository;
import org.springframework.data.domain.Page;

import java.util.UUID;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioRepositoryImpl(final UsuarioJpaRepository usuarioJpaRepository, final UsuarioMapper usuarioMapper) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Usuario save(final Usuario usuario) {
        var usuarioEntity = this.usuarioMapper.fromEntityJpa(usuario);
        this.usuarioJpaRepository.save(usuarioEntity);
        return this.usuarioMapper.fromEntityDomain(usuarioEntity);
    }

    @Override
    public Usuario update(final Usuario usuario) {
        var usuarioEntity = this.usuarioMapper.fromEntityJpa(usuario);
        this.usuarioJpaRepository.save(usuarioEntity);
        return this.usuarioMapper.fromEntityDomain(usuarioEntity);
    }

    @Override
    public Boolean deactivate(final UUID uuid) {
        return this.usuarioJpaRepository.getReferenceById(uuid).getAtivo();
    }

    @Override
    public Boolean reactivate(final UUID uuid) {
        return this.usuarioJpaRepository.getReferenceById(uuid).getAtivo();
    }

    @Override
    public Usuario findById(final UUID uuid) {
        var usuarioEntity = this.usuarioJpaRepository.getReferenceById(uuid);
        return this.usuarioMapper.fromEntityDomain(usuarioEntity);
    }

    @Override
    public Page<Usuario> findAll() {
        var usuarioEntityList = this.usuarioJpaRepository.findAll();
        return this.usuarioMapper.fromUsuarioPage(usuarioEntityList);
    }
}
