package gateway.br.com.gateway.presentation.mapper.usuarios;

import gateway.br.com.gateway.application.dto.enfermeiro.AtualizaEnfermeiroDTO;
import gateway.br.com.gateway.application.dto.enfermeiro.CadastraEnfermeiroDTO;
import gateway.br.com.gateway.application.dto.enfermeiro.EnfermeiroResponseDTO;
import gateway.br.com.gateway.application.mapper.enfermeiro.IEnfermeiroMapper;
import gateway.br.com.gateway.domain.model.usuario.Enfermeiro;
import gateway.br.com.gateway.domain.model.valueobject.Coren;
import gateway.br.com.gateway.infrastructure.persistence.entity.usuarios.JpaEnfermeiroEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnfermeiroMapper implements IEnfermeiroMapper {
    @Override
    public List<EnfermeiroResponseDTO> mapList(List<Enfermeiro> enfermeiros) {
        if (enfermeiros == null || enfermeiros.isEmpty()) {
            return Collections.emptyList();
        }
        return enfermeiros.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EnfermeiroResponseDTO toResponseDTO(Enfermeiro enfermeiro) {
        if (enfermeiro == null) {
            return null;
        }
        return new EnfermeiroResponseDTO(enfermeiro.getId(),
                enfermeiro.getNome(),
                enfermeiro.getEmail(),
                enfermeiro.getCoren(),
                enfermeiro.isAtivo(),
                enfermeiro.getCriadoEm(),
                enfermeiro.getAtualizadoEm());
    }

    @Override
    public JpaEnfermeiroEntity toJpaEnfermeiroEntity(Enfermeiro enfermeiro) {
        JpaEnfermeiroEntity jpaEnfermeiroEntity = new JpaEnfermeiroEntity();
        jpaEnfermeiroEntity.setId(enfermeiro.getId());
        jpaEnfermeiroEntity.setNome(enfermeiro.getNome());
        jpaEnfermeiroEntity.setEmail(enfermeiro.getEmail());
        jpaEnfermeiroEntity.setSenha(enfermeiro.getSenha());
        jpaEnfermeiroEntity.setCoren(enfermeiro.getCoren());
        jpaEnfermeiroEntity.setAtivo(enfermeiro.isAtivo());
        jpaEnfermeiroEntity.setCriadoEm(enfermeiro.getCriadoEm());
        jpaEnfermeiroEntity.setAtualizadoEm(enfermeiro.getAtualizadoEm());
        return jpaEnfermeiroEntity;
    }

    @Override
    public Enfermeiro toDomain(CadastraEnfermeiroDTO cadastraEnfermeiroDTO) {
        return new Enfermeiro(cadastraEnfermeiroDTO.nome(),
                cadastraEnfermeiroDTO.email(),
                cadastraEnfermeiroDTO.senha(),
                Coren.of(cadastraEnfermeiroDTO.coren()));
    }

    @Override
    public Enfermeiro toDomain(AtualizaEnfermeiroDTO atualizaEnfermeiroDTO, Enfermeiro enfermeiroExistente) {
        enfermeiroExistente.setNome(atualizaEnfermeiroDTO.nome());
        enfermeiroExistente.setEmail(atualizaEnfermeiroDTO.email());
        enfermeiroExistente.setCoren(Coren.of(atualizaEnfermeiroDTO.coren()));
        return enfermeiroExistente;
    }

    @Override
    public Enfermeiro toDomain(JpaEnfermeiroEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        return new Enfermeiro(jpaEntity.getId(),
                jpaEntity.getNome(),
                jpaEntity.getEmail(),
                jpaEntity.getSenha(),
                jpaEntity.getCoren(),
                jpaEntity.isAtivo(),
                jpaEntity.getCriadoEm(),
                jpaEntity.getAtualizadoEm());
    }
}
