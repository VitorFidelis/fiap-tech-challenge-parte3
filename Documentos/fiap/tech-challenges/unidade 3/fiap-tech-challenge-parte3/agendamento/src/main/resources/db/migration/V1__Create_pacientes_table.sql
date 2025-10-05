-- Tabela para pacientes
CREATE TABLE pacientes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    versao BIGINT DEFAULT 0,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT true,
    role VARCHAR(50) NOT NULL CHECK (role IN ('PACIENTE', 'MEDICO', 'ENFERMEIRO')),
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Índices para pacientes
CREATE INDEX idx_pacientes_email ON pacientes(email);
CREATE INDEX idx_pacientes_ativo ON pacientes(ativo);
CREATE INDEX idx_pacientes_data_nascimento ON pacientes(data_nascimento);
CREATE INDEX idx_pacientes_role ON pacientes(role);