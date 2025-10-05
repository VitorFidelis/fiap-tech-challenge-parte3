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

responsável pela criação e edição das consultas; realizado em REST e GraphQL.

### Serviço de notificações:

envia lembretes automáticos aos pacientes sobre consultas futuras.

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

Preencha esse arquivo **.env** criado, com as credenciais desejadas (aleatório).

```Text
# Configuração PostgreSQL (todos os serviços usam)
POSTGRES_USER=info_user_name
POSTGRES_PASSWORD=info_password

# Configuração RabbitMQ
RABBITMQ_USER=info_user_name
RABBITMQ_PASSWORD=info_password
```
Com essas informações preenchidas o docker-compose.yml consegue criar as bases de dados no postgreSQL

### Execute com Docker compose

````Shell
  docker compose up
````

Depois do comando a cima, podemos validar se os containers estão em execução:

```Shell
  docker compose ps
```

## Como testar:
Há uma coleção do postman na pasta Resource, para teste dos endpoints em REST. Para os endpoints em GraphQL, é preciso utilizar o link:

```
http://localhost:8080/graphiql?path=/graphql
```

## 🧩​Time de Desenvolvimento

- Eric Monné: [GitHub](https://github.com/ericmonne) |

- Lucas Vinicius: [GitHub](https://github.com/lcvinicius) |

- Ana Cortez: [GitHub](https://github.com/anacarolcortez) |

- Vitor Fidelis: [GitHub](https://github.com/VitorFidelis) |

