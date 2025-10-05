-- Tabela para consultas
CREATE TABLE consultas (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    versao BIGINT DEFAULT 0,
    medico_id UUID NOT NULL,
    enfermeiro_id UUID,
    paciente_id UUID NOT NULL,
    horario_solicitado TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL CHECK (status IN ('AGENDADA', 'CANCELADA', 'REALIZADA')),
    observacoes TEXT,
    ativo BOOLEAN NOT NULL DEFAULT true,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Chaves estrangeiras
    CONSTRAINT fk_consulta_medico FOREIGN KEY (medico_id) REFERENCES medicos(id),
    CONSTRAINT fk_consulta_enfermeiro FOREIGN KEY (enfermeiro_id) REFERENCES enfermeiros(id),
    CONSTRAINT fk_consulta_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);

-- Índices para consultas
CREATE INDEX idx_consultas_medico_id ON consultas(medico_id);
CREATE INDEX idx_consultas_enfermeiro_id ON consultas(enfermeiro_id);
CREATE INDEX idx_consultas_paciente_id ON consultas(paciente_id);
CREATE INDEX idx_consultas_horario_solicitado ON consultas(horario_solicitado);
CREATE INDEX idx_consultas_status ON consultas(status);
CREATE INDEX idx_consultas_ativo ON consultas(ativo);

-- Índices únicos para evitar conflitos de agendamento
CREATE UNIQUE INDEX idx_consultas_medico_horario_unique
ON consultas(medico_id, horario_solicitado)
WHERE status = 'AGENDADA' AND ativo = true;

CREATE UNIQUE INDEX idx_consultas_paciente_horario_unique
ON consultas(paciente_id, horario_solicitado)
WHERE status = 'AGENDADA' AND ativo = true;