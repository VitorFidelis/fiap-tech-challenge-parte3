CREATE TABLE tipo_usuarios(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) UNIQUE NOT NULL,
    descricao TEXT,
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE usuarios(
    id BINARY(16) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(50),
    email VARCHAR(255) NOT NULL,
    senha VARCHAR NOT NULL,
    tipousuario_id BIGINT NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (tipousuario_id) REFERENCES tipo_usuarios(id)
);