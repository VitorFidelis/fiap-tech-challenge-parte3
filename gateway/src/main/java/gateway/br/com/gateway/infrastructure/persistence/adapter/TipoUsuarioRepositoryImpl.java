package gateway.br.com.gateway.infrastructure.persistence.adapter;

import gateway.br.com.gateway.domain.model.TipoUsuario;
import gateway.br.com.gateway.domain.repository.TipoUsuarioRepository;
import gateway.br.com.gateway.infrastructure.persistence.mapper.TipoUsuarioMapper;
import gateway.br.com.gateway.infrastructure.persistence.springdata.TipoUsuarioJpaRepository;
import org.springframework.data.domain.Page;


public class TipoUsuarioRepositoryImpl implements TipoUsuarioRepository {
    private final TipoUsuarioJpaRepository tipoUsuarioJpaRepository;
    private final TipoUsuarioMapper tipoUsuarioMapper;

    public TipoUsuarioRepositoryImpl(TipoUsuarioJpaRepository tipoUsuarioJpaRepository, TipoUsuarioMapper tipoUsuarioMapper) {
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
    public Boolean dectivate(Long id) {
        return this.tipoUsuarioJpaRepository.getReferenceById(id).getAtivo();
    }

    @Override
    public Boolean reactivate(Long id) {
        return this.tipoUsuarioJpaRepository.getReferenceById(id).getAtivo();
    }

    @Override
    public Page<TipoUsuario> findAll() {
        var tipoUsuarioEntityList = this.tipoUsuarioJpaRepository.findAll();
        return this.tipoUsuarioMapper.fromTipoUsuarioDomainPage(tipoUsuarioEntityList);
    }
}
