package gateway.br.com.gateway.presentation.mapper.usuarios;

import gateway.br.com.gateway.application.dto.medico.AtualizaMedicoDTO;
import gateway.br.com.gateway.application.dto.medico.CadastraMedicoDTO;
import gateway.br.com.gateway.application.dto.medico.MedicoResponseDTO;
import gateway.br.com.gateway.application.mapper.medico.IMedicoMapper;
import gateway.br.com.gateway.domain.model.usuario.Medico;
import gateway.br.com.gateway.infrastructure.datasource.jpa.medico.JpaMedicoEntity;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicoMapper implements IMedicoMapper {
    @Override
    public List<MedicoResponseDTO> mapList(List<Medico> medicos) {
        if (medicos == null || medicos.isEmpty()) return Collections.emptyList();
        return medicos.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicoResponseDTO toResponseDTO(Medico medico) {
        if (medico == null) return null;
        return new MedicoResponseDTO(medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.isAtivo(),
                medico.getCriadoEm(),
                medico.getAtualizadoEm());
    }

    @Override
    public JpaMedicoEntity toJpaMedicoEntity(Medico medico) {
        JpaMedicoEntity jpaEntity = new JpaMedicoEntity();
        jpaEntity.setId(medico.getId());
        jpaEntity.setNome(medico.getNome());
        jpaEntity.setEmail(medico.getEmail());
        jpaEntity.setSenha(medico.getSenha());
        jpaEntity.setCrm(medico.getCrm());
        jpaEntity.setEspecialidade(medico.getEspecialidade());
        jpaEntity.setAtivo(medico.isAtivo());
        jpaEntity.setCriadoEm(medico.getCriadoEm());
        jpaEntity.setAtualizadoEm(medico.getAtualizadoEm());
        return jpaEntity;
    }

    @Override
    public Medico toDomain(CadastraMedicoDTO cadastraMedicoDTO) {
        return new Medico(
                cadastraMedicoDTO.nome(),
                cadastraMedicoDTO.email(),
                cadastraMedicoDTO.senha(),
                cadastraMedicoDTO.crm(),
                cadastraMedicoDTO.especialidade()
        );
    }

    @Override
    public Medico toDomain(AtualizaMedicoDTO atualizaMedicoDTO, Medico medicoExistente) {
        medicoExistente.setNome(atualizaMedicoDTO.nome());
        medicoExistente.setEmail(atualizaMedicoDTO.email());
        medicoExistente.setSenha(atualizaMedicoDTO.senha());
        medicoExistente.setCrm(atualizaMedicoDTO.crm());
        medicoExistente.setEspecialidade(atualizaMedicoDTO.especialidade());
        return medicoExistente;
    }

    @Override
    public Medico toDomainFromJPA(JpaMedicoEntity jpaEntity) {
        if (jpaEntity == null) return null;
        return new Medico(jpaEntity.getId(),
                jpaEntity.getNome(),
                jpaEntity.getEmail(),
                jpaEntity.getSenha(),
                jpaEntity.getCrm(),
                jpaEntity.getEspecialidade(),
                jpaEntity.isAtivo(),
                jpaEntity.getCriadoEm(),
                jpaEntity.getAtualizadoEm());
    }
}
