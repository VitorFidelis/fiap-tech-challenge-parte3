# fiap-tech-challenge-parte3
Desenvolvimento de uma API RESTful que garante o agendamento eficaz de consultas, o gerenciamento do histórico de pacientes e o envio de lembretes automáticos para garantir a presença dos pacientes nas consultas. Este sistema vai ser acessível a diferentes tipos de usuários (médicos, enfermeiros e pacientes), com acesso controlado e funcionalidades específicas para cada perfil.

---

## 📔​agendio-api

### Problema:

Em um ambiente hospitalar, é essencial contar com sistemas que
garantam o agendamento eficaz de consultas, o gerenciamento do histórico de
pacientes e o envio de lembretes automáticos para garantir a presença dos
pacientes nas consultas. Este sistema deve ser acessível a diferentes tipos de
usuários (médicos, enfermeiros e pacientes), com acesso controlado e
funcionalidades específicas para cada perfil.

### Objetivo:

O objetivo é desenvolver um backend simplificado e modular, com foco
em segurança e comunicação assíncrona, garantindo que o sistema seja
escalável, seguro e que utilize boas práticas de autenticação, autorização e
comunicação entre serviços.

---

## 👨‍🔧​separação em mais de um serviço

### Serviço de agendamento:

responsável pela criação e edição das consultas.

### Serviço de notificações:

envia lembretes automáticos aos pacientes sobre consultas futuras.

### Serviço de histórico:

armazena o histórico de consultas e disponibiliza dados via GraphQL.

---

## 📝​Tecnologias

- Java 21+
- Spring Boot 3
- Maven
- Spring Security + JWT
- PostgreSQL
- RabbitMQ
- GraphQL
- Spring Data JPA
- SpringDoc / Swagger
- Docker
- Docker Compose

---

## ▶️​Como executar

primeiro instale todas as dependencias necessarias:

```shell
  mvn clean install
```
### Variaveis de Ambiente

identifique o arquivo ***.env.example*** e faça uma copia renomeando para ***.env***.

Preencha esse arquivo **.env** criado, com as credenciais que são utilizadas para acessar o PostgreSQL
e RabbitMQ de sua maquina:

```Text
# Agendamento DB
DB_AGENDAMENTO_NAME=
DB_AGENDAMENTO_USER=
DB_AGENDAMENTO_PASSWORD=
DB_AGENDAMENTO_PORT=

# Notificação DB
DB_NOTIFICACAO_NAME=
DB_NOTIFICACAO_USER=
DB_NOTIFICACAO_PASSWORD=
DB_NOTIFICACAO_PORT=

# Histórico DB
DB_HISTORICO_NAME=
DB_HISTORICO_USER=
DB_HISTORICO_PASSWORD=
DB_HISTORICO_PORT=

# RabbitMQ
RABBITMQ_USER=
RABBITMQ_PASSWORD=
RABBITMQ_PORT=
RABBITMQ_MANAGEMENT_PORT=
```
Com essas informações preenchidas o docker-compose.yml consegue acessar as bases de dados.

### Execute com Docker compose

Baixe todos os microsserviços e no seu console favorito rodar:

Se você tem docker e docker compose instalados, você pode executar apenas a execução:

````Shell
  docker-compose.yml up
````

### Executar com a linha de comando spring-boot:run

Após executar o seguinte comando:

⚠️ ​Importante: certifique-se de que você está no mesmo diretório de pom.xml Em cada módulo executado:

```Shell
  mvn spring-boot:run
```

link para mais! [info-docker](guide/info-docker.md).

---

## 🧩​Time de Desenvolvimento

- Eric Mone: [GitHub](https://github.com/ericmonne) |

- Lucas Vinicius: [GitHub](https://github.com/lcvinicius) |

- Ana Cortez: [GitHub](https://github.com/anacarolcortez) |

- Vitor Fidelis: [GitHub](https://github.com/VitorFidelis) |

