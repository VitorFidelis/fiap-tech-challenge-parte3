-- Criar tabela tipo_usuarios com auto-incremento
CREATE TABLE IF NOT EXISTS tipo_usuarios (
    id BIGSERIAL PRIMARY KEY,  -- auto-incremento
    nome VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL
);

-- Criar tabela usuarios com UUID automático
CREATE TABLE IF NOT EXISTS usuarios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha TEXT NOT NULL,
    tipousuario_id BIGINT NOT NULL,
    FOREIGN KEY (tipousuario_id) REFERENCES tipo_usuarios(id)
);