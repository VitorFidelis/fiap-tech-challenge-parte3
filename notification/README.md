# Serviço de Notificações

Microserviço responsável por **consumir eventos de consultas** a partir do RabbitMQ e **registrar lembretes automáticos** (simulação por log/console) com **auditoria consultável por HTTP**.

Fluxo E2E:  
**Agendamento → RabbitMQ (`consultas.eventos`) → Notificações → Auditoria**

---

## ⚙️ Requisitos

- Java 21
- Maven 3.9+
- Docker e Docker Compose (para subir RabbitMQ e bancos)

---

## 🚀 Subindo

### Via Docker Compose (recomendado)
O `docker-compose.yml` da raiz já mapeia o serviço do **notificacao-service**:

- **Portas**: a aplicação roda **dentro do container** em `8081` e está mapeada como **`8082:8081`**  
  - Do **host** (Postman/curl): `http://localhost:8082`  
  - De **outro container** na mesma rede: `http://notificacao-service:8081` (use o *service name* do compose)

Suba:
```bash
docker compose up -d
docker compose logs -f notificacao-service
```

### Via Maven (local, sem container)
```bash
# certifique-se de ter um RabbitMQ rodando (ver configuração abaixo)
mvn spring-boot:run
# app em http://localhost:8081
```

---

## 🔧 Configuração

Use variáveis de ambiente (ou `application.properties`) para apontar o RabbitMQ e a fila:

```properties
# RabbitMQ
spring.rabbitmq.host=rabbitmq        # em container: use o nome do serviço do RabbitMQ (ex.: rabbitmq)
# spring.rabbitmq.host=localhost     # se rodando localmente fora de container
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

# Fila de consumo
notificacoes.queue=consultas.eventos
```

> Dica: se o **Agendamento** rodar em container, ele deve publicar usando host `rabbitmq` (não `localhost`).  
> Este serviço **consome** da mesma fila.

---

## 📥 Evento esperado (payload)

O consumidor lê **JSON** da fila `consultas.eventos`. Exemplos:

### Criar consulta
```json
{
  "tipo": "CONSULTA_CRIADA",
  "consultaId": "IdConsulta",
  "dataHoraConsulta": "2025-10-05T14:00:00-03:00",
  "pacienteContato": "email@paciente.com"  // opcional
}
```

### Editar consulta
```json
{
  "tipo": "CONSULTA_EDITADA",
  "consultaId": "IdConsulta",
  "dataHoraConsulta": "2025-10-05T16:00:00-03:00",
  "pacienteContato": "email@paciente.com"  // opcional
}
```


> **Importante:** `dataHoraConsulta` deve ser **ISO-8601 com offset** (ex.: `…-03:00`) ou em `UTC` (`...Z`).  
> Se `pacienteContato` não vier, o serviço assume `"desconhecido"` na simulação.

---

## 🔁 Lógica de processamento

- Ao receber `CONSULTA_CRIADA` ou `CONSULTA_EDITADA`, o serviço registra **2 lembretes**:
  - **T-24h** da `dataHoraConsulta`
  - **T-2h** da `dataHoraConsulta`
- Ao receber `CONSULTA_CANCELADA`, marca a consulta como cancelada (não agenda novos lembretes).
- **Simulação**: o envio é registrado por **logs** (ex.: `[LEMBRETE] ... -> Enviando lembrete ao paciente...`).
- **Retentativa**: em falha de processamento, aplica **backoff simples** e tenta novamente (logs mostram tentativas/espera).

---

## 🧭 Endpoints

Base URL (host): `http://localhost:8082`  
Base URL (container/rede compose): `http://notificacao-service:8081`

### Health
```http
GET /actuator/health
```

### Auditoria de lembretes por consulta
```http
GET /lembretes/consulta/{consultaId}
```
**Resposta (exemplo):**
```json
[
  {
    "consultaId": "IdConsulta",
    "whenLabel": "T-24h",
    "scheduledAt": "2025-10-04T14:00:00-03:00",
    "status": "SENT",
    "note": "Enviado (simulado)"
  },
  {
    "consultaId": "IdConsulta",
    "whenLabel": "T-2h",
    "scheduledAt": "2025-10-05T12:00:00-03:00",
    "status": "SENT",
    "note": "Enviado (simulado)"
  }
]
```

---

## 🧪 Testes rápidos

### 1) Painel do RabbitMQ
```
http://localhost:15672  (guest/guest)
```
Fila esperada: `consultas.eventos`.

### 2) Publicar manualmente pelo RabbitMQ UI
- **Queues → consultas.eventos → Publish message**
- `Routing key`: `consultas.eventos`
- **Payload** (JSON acima) e **Content type**: `application/json`
- Clique “Publish message” e veja logs do serviço.

### 3) Conferir auditoria
```bash
curl http://localhost:8082/lembretes/consulta/IdConsulta
```

---

## 🪵 Logs esperados
Sucesso:
```
INFO  ConsultationEventListener - Evento recebido: tipo=CONSULTA_CRIADA consultaId=abc-123 dataHora=2025-10-05T14:00-03:00
INFO  RegisterRemindersUseCase - [LEMBRETE] consultaId=abc-123 label=T-24h scheduledAt=2025-10-04T14:00-03:00 -> Enviando lembrete ao paciente...
INFO  RegisterRemindersUseCase - [LEMBRETE] consultaId=abc-123 label=T-2h  scheduledAt=2025-10-05T12:00-03:00 -> Enviando lembrete ao paciente...
```

Erro de parse (payload não-JSON):
```
WARN ConsultationEventListener - Falha ao processar mensagem (tentativa 1/5) ... Unexpected character ...
```

---

## 🔍 Troubleshooting

- **ConnectException localhost:5672 (Connection refused)**  
  - Em container, **NÃO** use `localhost`. Configure `spring.rabbitmq.host=rabbitmq` (nome do serviço no Compose).
  - Verifique RabbitMQ rodando: `docker compose ps` e painel `:15672`.

- **JSON inválido ao consumir**  
  - O produtor (Agendamento) deve enviar **JSON** (String) ou usar `Jackson2JsonMessageConverter`.
  - Publicando pelo painel do RabbitMQ, marque **Content type: application/json**.

- **Fila com mensagens “Unacked”**  
  - Entregues mas não confirmadas (em retry). Para limpar: `Queues → consultas.eventos → Purge`.

- **Horário “errado” nos lembretes**  
  - Envie `dataHoraConsulta` com offset (`-03:00`) ou em UTC (`...Z`).

---

## 📦 Build
```bash
mvn -DskipTests package
# target/*.jar
```

---

## 📚 Resumo
- Consome **`consultas.eventos`** no RabbitMQ.  
- Cria **lembretes automáticos** (log/console) em **T-24h** e **T-2h**.  
- **Auditoria HTTP** em `GET /lembretes/consulta/{consultaId}`.  
- **Retry** simples em falha.  
- Integra com **Agendamento** publicando JSON.
