WITH novo_tipo AS (
    INSERT INTO tipo_usuarios (nome, descricao)
    VALUES ('SUPERADMIN', 'Usuario root do sistema')
    RETURNING id
)
INSERT INTO usuarios (nome, sobrenome, email, senha, tipousuario_id)
SELECT 'Root', 'Sistema', 'root@exemplo.com',
       '$2a$12$IOOf27Hus2ZYszut74isYOApTmrmTaqWXRhvnhDfmDN0CcHowJzg.',-- Root@#$123
       id
FROM novo_tipo;