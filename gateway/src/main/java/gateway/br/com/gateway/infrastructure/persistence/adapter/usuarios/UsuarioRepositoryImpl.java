package gateway.br.com.gateway.infrastructure.persistence.adapter.usuarios;

import gateway.br.com.gateway.domain.model.usuario.Usuario;
import gateway.br.com.gateway.domain.repository.usuarios.UsuarioRepository;
import gateway.br.com.gateway.infrastructure.persistence.mapper.usuarios.UsuarioInfraMapper;
import gateway.br.com.gateway.infrastructure.persistence.springdata.usuarios.UsuarioJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioInfraMapper usuarioInfraMapper;

    public UsuarioRepositoryImpl(
            final UsuarioJpaRepository usuarioJpaRepository,
            final UsuarioInfraMapper usuarioInfraMapper
    ) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.usuarioInfraMapper = usuarioInfraMapper;
    }

    @Override
    public Usuario save(final Usuario usuario) {
        var usuarioEntity = this.usuarioInfraMapper.fromEntityJpa(usuario);
        this.usuarioJpaRepository.save(usuarioEntity);
        return this.usuarioInfraMapper.fromEntityDomain(usuarioEntity);
    }

    @Override
    public Usuario update(final Usuario usuario) {
        var usuarioEntity = this.usuarioInfraMapper.fromEntityJpa(usuario);
        this.usuarioJpaRepository.save(usuarioEntity);
        return this.usuarioInfraMapper.fromEntityDomain(usuarioEntity);
    }

    @Override
    public Usuario deactivate(final UUID uuid) {
        var usuarioEntity = this.usuarioJpaRepository.getReferenceById(uuid);
        return this.usuarioInfraMapper.fromEntityDomain(usuarioEntity);
    }

    @Override
    public Usuario reactivate(final UUID uuid) {
        var usuarioEntity = this.usuarioJpaRepository.getReferenceById(uuid);
        return this.usuarioInfraMapper.fromEntityDomain(usuarioEntity);
    }

    @Override
    public Usuario findById(final UUID uuid) {
        var usuarioEntity = this.usuarioJpaRepository.getReferenceById(uuid);
        return this.usuarioInfraMapper.fromEntityDomain(usuarioEntity);
    }

    @Override
    public Page<Usuario> findAll(final Pageable pageable) {
        var usuarioEntityPage =  this.usuarioJpaRepository.findAll(pageable);
        return this.usuarioInfraMapper.fromUsuarioPage(usuarioEntityPage);
    }

    @Override
    public Usuario findByEmail(final String email) {
        var usuarioEntity  = this.usuarioJpaRepository.findByEmail(email);
        return this.usuarioInfraMapper.fromEntityDomain(usuarioEntity);
    }
}
