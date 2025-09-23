 -- Criar TIPO_USUARIOS SUPERADMIN
 INSERT INTO tipo_usuarios (nome, descricao) VALUES('SUPERADMIN', 'Usuario root do sistema');

 -- Criar usuário root
 INSERT INTO usuarios (nome, sobrenome, email, senha, tipousuario_id)
 VALUES ('root', 'root', 'root@gateway.com', '$2a$12$COl8MKeulV9tfuoqYEkqauKwiWnB5sAB76URXCObQ.2q2Ewz4SOIy', 1); -- senha root