-- Tabela para enfermeiros
CREATE TABLE enfermeiros (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    versao BIGINT DEFAULT 0,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    coren VARCHAR(15) UNIQUE NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT true,
    role VARCHAR(50) NOT NULL CHECK (role IN ('PACIENTE', 'MEDICO', 'ENFERMEIRO')),
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Índices para enfermeiros
CREATE INDEX idx_enfermeiros_email ON enfermeiros(email);
CREATE INDEX idx_enfermeiros_coren ON enfermeiros(coren);
CREATE INDEX idx_enfermeiros_ativo ON enfermeiros(ativo);
CREATE INDEX idx_enfermeiros_role ON enfermeiros(role);