# 🩺 fiap-tech-challenge-parte3

## Visão Geral do Projeto

Este projeto consiste no **Desenvolvimento de uma API RESTful** focada em soluções de agendamento e gestão de pacientes para um ambiente hospitalar. O sistema garante o **agendamento eficaz de consultas**, o **gerenciamento do histórico de pacientes** e o **envio de lembretes automáticos** para garantir a presença dos pacientes.

O sistema é construído de forma **modular e escalável**, utilizando comunicação assíncrona para o envio de notificações e acesso controlado a diferentes perfis de usuários (médicos, enfermeiros e pacientes).

-----

## 🎯 Objetivo e Solução

### Problema:

Em um ambiente hospitalar, é essencial garantir o **agendamento eficaz de consultas**, o **gerenciamento do histórico de pacientes** e o **envio de lembretes automáticos**. O sistema deve ser acessível a diferentes tipos de usuários (médicos, enfermeiros e pacientes), com **acesso controlado** e funcionalidades específicas para cada perfil.

### Solução (Objetivo):

O objetivo é desenvolver um *backend* simplificado e modular, com foco em segurança e comunicação assíncrona. Isso garante que o sistema seja **escalável**, **seguro** e utilize boas práticas de **autenticação (JWT)**, **autorização** e **comunicação entre serviços**.

-----

## 👨‍🔧 Arquitetura e Microsserviços

A aplicação é dividida em serviços para garantir a **modularidade** e a **comunicação assíncrona**:

### 1\. Serviço de Agendamento (`agendio-api`)

  * **Função:** Responsável pela **criação e edição das consultas** e pelo gerenciamento do histórico de pacientes.
  * **Endpoints:** Implementado em **REST** (para operações padrão) e **GraphQL** (para consultas de dados complexas).
  * **Persistência:** Utiliza **PostgreSQL** através do Spring Data JPA.

### 2\. Serviço de Notificações

  * **Função:** Responsável exclusivamente por enviar **lembretes automáticos** aos pacientes sobre consultas futuras.
  * **Comunicação:** Utiliza **RabbitMQ** para receber de forma assíncrona os eventos de agendamento e processar o envio dos lembretes.

-----

## 📝 Tecnologias Utilizadas

| Categoria | Tecnologia | Justificativa |
| :--- | :--- | :--- |
| **Backend** | **Java 21+** e **Spring Boot 3**  | Base robusta e madura para o desenvolvimento da API. |
| **Persistência** | **PostgreSQL**  e **Spring Data JPA**  | Banco de dados relacional para garantir a **integridade e consistência** dos dados de agendamento e histórico. |
| **Mensageria** | **RabbitMQ**  | Permite o envio **assíncrono** dos lembretes, garantindo que o serviço principal de agendamento não seja travado. |
| **Segurança** | **Spring Security + JWT** | Implementação de autenticação e autorização seguras para os diferentes perfis (médicos, enfermeiros e pacientes). |
| **API/Query** | **REST**, **GraphQL**  e **SpringDoc / Swagger** | Oferece flexibilidade e uma documentação interativa para os *endpoints*. |
| **Infraestrutura** | **Docker** e **Docker Compose** | Facilita a instalação e a execução de todos os serviços (API, PostgreSQL e RabbitMQ) em um único comando. |

-----

## ▶️ Como Executar o Projeto

Siga os passos abaixo para subir a aplicação completa em seu ambiente local usando Docker Compose:

### 1\. Configuração de Variáveis de Ambiente

Crie o arquivo de variáveis de ambiente com base no modelo:

  * Identifique o arquivo ***.env.example*** e faça uma cópia renomeando-a para **.env**.
  * Preencha o novo arquivo **.env** com as credenciais que deseja usar. O Docker Compose usará essas credenciais para configurar o PostgreSQL e o RabbitMQ:

<!-- end list -->

```text
# Configuração PostgreSQL (todos os serviços usam)
POSTGRES_USER=info_user_name 
POSTGRES_PASSWORD=info_password 

# Configuração RabbitMQ
RABBITMQ_USER=info_user_name 
RABBITMQ_PASSWORD=info_password 
```

### 2\. Inicialização dos Containers

Use o Docker Compose para criar e iniciar todos os serviços (banco de dados, *message broker* e a API):

```shell
docker compose up
```

Após a execução, valide se todos os *containers* estão ativos:

```shell
docker compose ps
```

*Opcional: Se desejar reconstruir a imagem da API, execute:* `mvn clean install`.

-----

## Como Testar a API

### Teste da API REST (POSTMAN)

Para testar os *endpoints* em REST, utilize a **coleção do Postman** fornecida na pasta `Resource` do projeto.

### Teste da API GraphQL

Para consultar os *endpoints* implementados em **GraphQL**, acesse o *playground* do GraphiQL no seu navegador:

```
http://localhost:8080/graphiql?path=/graphql 
```

-----

## 🧩 Time de Desenvolvimento

O projeto foi desenvolvido pelo seguinte time:

  * Eric Monné: [GitHub](https://github.com/ericmonne) 
  * Lucas Vinicius: [GitHub](https://github.com/lcvinicius) 
  * Ana Cortez: [GitHub](https://github.com/anacarolcortez) 
  * Vitor Fidelis: [GitHub](https://github.com/VitorFidelis) 
