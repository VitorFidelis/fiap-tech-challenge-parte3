-- Tabela para médicos
CREATE TABLE medicos (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    versao BIGINT DEFAULT 0,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    crm VARCHAR(20) UNIQUE NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT true,
    role VARCHAR(50) NOT NULL CHECK (role IN ('PACIENTE', 'MEDICO', 'ENFERMEIRO')),
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Índices para médicos
CREATE INDEX idx_medicos_email ON medicos(email);
CREATE INDEX idx_medicos_crm ON medicos(crm);
CREATE INDEX idx_medicos_especialidade ON medicos(especialidade);
CREATE INDEX idx_medicos_ativo ON medicos(ativo);
CREATE INDEX idx_medicos_role ON medicos(role);