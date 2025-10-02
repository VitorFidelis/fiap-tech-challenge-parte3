CREATE TABLE IF NOT EXISTSconsultas (
    id UUID PRIMARY KEY,
    versao BIGINT NOT NULL,
    medico_id UUID NOT NULL,
    enfermeiro_id UUID NOT NULL,
    paciente_id UUID NOT NULL,
    horario_solicitado TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status VARCHAR(50) NOT NULL,
    observacoes TEXT,

    -- Auditoria
    criado_em TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    atualizado_em TIMESTAMP WITHOUT TIME ZONE,
    ativo BOOLEAN NOT NULL,

    -- Definição das Chaves Estrangeiras
    CONSTRAINT fk_medico
        FOREIGN KEY (medico_id)
        REFERENCES medicos (id),

    CONSTRAINT fk_enfermeiro
        FOREIGN KEY (enfermeiro_id)
        REFERENCES enfermeiros (id),

    CONSTRAINT fk_paciente
        FOREIGN KEY (paciente_id)
        REFERENCES pacientes (id)
);