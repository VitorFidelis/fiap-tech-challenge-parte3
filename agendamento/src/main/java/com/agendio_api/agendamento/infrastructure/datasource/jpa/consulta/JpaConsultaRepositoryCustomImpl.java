package com.agendio_api.agendamento.infrastructure.datasource.jpa.consulta;

import com.agendio_api.agendamento.domain.model.consulta.StatusConsulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class JpaConsultaRepositoryCustomImpl implements JpaConsultaRepositoryCustom {

    private final EntityManager em;

    public JpaConsultaRepositoryCustomImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<JpaConsultaEntity> findByFilter(UUID pacienteId, UUID medicoId, UUID enfermeiroId,
                                                StatusConsulta statusConsulta, LocalDateTime dataMin,
                                                LocalDateTime dataMax) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<JpaConsultaEntity> cq = cb.createQuery(JpaConsultaEntity.class);
        Root<JpaConsultaEntity> root = cq.from(JpaConsultaEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (pacienteId != null) predicates.add(cb.equal(root.get("pacienteId"), pacienteId));
        if (medicoId != null) predicates.add(cb.equal(root.get("medicoId"), medicoId));
        if (enfermeiroId != null) predicates.add(cb.equal(root.get("enfermeiroId"), enfermeiroId));
        if (statusConsulta != null) predicates.add(cb.equal(root.get("status"), statusConsulta));
        if (dataMin != null) predicates.add(cb.greaterThanOrEqualTo(root.get("horarioSolicitado"), dataMin));
        if (dataMax != null) predicates.add(cb.lessThanOrEqualTo(root.get("horarioSolicitado"), dataMax));

        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }
}
