## 🐳 Docker / Docker Compose

### Docker

O Docker é uma plataforma para empacotar, distribuir e executar aplicações e suas dependências em contêineres.

### Docker Compose

Enquanto o Docker Compose é uma ferramenta que facilita a definição e o gerenciamento 
de aplicações que utilizam múltiplos contêineres, orquestrando-os através de um único arquivo 
de configuração (geralmente em formato YAML).

## 📄​Arquivos Docker

No projeto nós temos um arquivo Dockerfile configurado em cada serviço, 
e um arquivo docker-compose.yml global na raiz do repositorio 
com as imagens das dependencias externas necessarias: PostgreSQL e RabbitMQ

### docker-compose.yml

Está configurado para ter uma base de dados para cada serviço.

#### db-agendamento:

| image        | ports  | container_name | volumes                                      |
|--------------|--------|----------------|----------------------------------------------|
| postgres:16  | :5432  | db-agendamento | db_agendamento_data:/var/lib/postgresql/data |

#### db-notificacao

| image        | ports  | container_name | volumes                                      |
|--------------|--------|----------------|----------------------------------------------|
| postgres:16  | :5432  | db-notificacao | db_notificacao_data:/var/lib/postgresql/data |

#### db-historico

| image        | ports  | container_name | volumes                                     |
|--------------|--------|----------------|---------------------------------------------|
| postgres:16  | :5432  | db-historico   | db_historico_data:/var/lib/postgresql/data  |

#### rabbitmq

| image                 | ports        | container_name |
|-----------------------|--------------|----------------|
| rabbitmq:3-management | :5672/:15672 | rabbitmq       |

No arquivo ***docker-compose.yml***, existem valores default (padrão), 
como o nome das bases de dados e variáveis de ambiente. Esses valores podem ser alterados conforme necessário
— por exemplo, no caso das portas: se a porta padrão já estiver em uso no seu ambiente, 
basta definir no arquivo ***.env*** a porta desejada.

Assim, no arquivo ***.env*** é obrigatório informar apenas o nome de usuário e senha do PostgreSQL e do RabbitMQ. 
Todos os demais parâmetros possuem valores padrão, mas podem ser sobrescritos, se necessário.