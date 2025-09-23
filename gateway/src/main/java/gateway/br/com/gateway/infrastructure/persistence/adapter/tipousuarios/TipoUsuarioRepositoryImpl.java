package gateway.br.com.gateway.infrastructure.persistence.adapter.tipousuarios;

import gateway.br.com.gateway.domain.model.tipousuarios.TipoUsuario;
import gateway.br.com.gateway.domain.repository.tipousuarios.TipoUsuarioRepository;
import gateway.br.com.gateway.infrastructure.persistence.mapper.tipousuarios.TipoUsuarioInfraMapper;
import gateway.br.com.gateway.infrastructure.persistence.springdata.tipousuarios.TipoUsuarioJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class TipoUsuarioRepositoryImpl implements TipoUsuarioRepository {
    private final TipoUsuarioJpaRepository tipoUsuarioJpaRepository;
    private final TipoUsuarioInfraMapper tipoUsuarioMapper;

    public TipoUsuarioRepositoryImpl(
            TipoUsuarioJpaRepository tipoUsuarioJpaRepository,
            TipoUsuarioInfraMapper tipoUsuarioMapper
    ) {
        this.tipoUsuarioJpaRepository = tipoUsuarioJpaRepository;
        this.tipoUsuarioMapper = tipoUsuarioMapper;
    }

    @Override
    public TipoUsuario save(TipoUsuario tipoUsuario) {
        var tipoUsuarioEntity = this.tipoUsuarioMapper.fromTipoUsuarioJpa(tipoUsuario);
        this.tipoUsuarioJpaRepository.save(tipoUsuarioEntity);
        return this.tipoUsuarioMapper.fromTipoUsuarioDomain(tipoUsuarioEntity);
    }

    @Override
    public TipoUsuario findById(Long id) {
        var tipoUsuarioEntity = this.tipoUsuarioJpaRepository.getReferenceById(id);
        return this.tipoUsuarioMapper.fromTipoUsuarioDomain(tipoUsuarioEntity);
    }

    @Override
    public TipoUsuario update(Long id) {
        var tipoUsuarioEntity = this.tipoUsuarioJpaRepository.getReferenceById(id);
        return this.tipoUsuarioMapper.fromTipoUsuarioDomain(tipoUsuarioEntity);
    }

    @Override
    public TipoUsuario dectivate(Long id) {
        var tipoUsuarioEntity = this.tipoUsuarioJpaRepository.getReferenceById(id);
        return this.tipoUsuarioMapper.fromTipoUsuarioDomain(tipoUsuarioEntity);
    }

    @Override
    public TipoUsuario reactivate(Long id) {
        var tipoUsuarioEntity = this.tipoUsuarioJpaRepository.getReferenceById(id);
        return this.tipoUsuarioMapper.fromTipoUsuarioDomain(tipoUsuarioEntity);
    }

    @Override
    public Page<TipoUsuario> findAll(Pageable pageable) {
        var tipoUsuarioEntityPage = this.tipoUsuarioJpaRepository.findAll(pageable);
        return this.tipoUsuarioMapper.fromTipoUsuarioDomainPage(tipoUsuarioEntityPage);
    }

    @Override
    public TipoUsuario findByNomeIgnoreCase(String nome) {
        var tipoUsuarioEntity = this.tipoUsuarioJpaRepository.findByNomeIgnoreCase(nome);
        return this.tipoUsuarioMapper.fromTipoUsuarioDomain(tipoUsuarioEntity);
    }
}
