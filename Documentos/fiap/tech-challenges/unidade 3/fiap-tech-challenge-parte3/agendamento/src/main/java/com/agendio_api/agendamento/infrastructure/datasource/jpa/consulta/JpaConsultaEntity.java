package com.agendio_api.agendamento.infrastructure.datasource.jpa.consulta;

import com.agendio_api.agendamento.domain.model.consulta.StatusConsulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consultas")
public class JpaConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Version
    @Column(name = "versao")
    private Long versao;

    @Column(name = "medico_id", nullable = false)
    private UUID medicoId;

    @Column(name = "enfermeiro_id")
    private UUID enfermeiroId;

    @Column(name = "paciente_id", nullable = false)
    private UUID pacienteId;

    @Column(name = "horario_solicitado", nullable = false)
    private LocalDateTime horarioSolicitado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConsulta status;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @Column(nullable = false)
    private boolean ativo;
}
