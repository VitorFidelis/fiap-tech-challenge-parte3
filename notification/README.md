# tech3 — Serviço de Notificações (v0.0.2)

**Atualização:** listener usa **TypeReference&lt;Map&lt;String,Object&gt;&gt;** para remover o warning de generics do Jackson.

## O que faz
- Consome mensagens da fila `consultas.eventos` (RabbitMQ).
- Agenda lembretes automáticos **T-24h** e **T-2h** (simulação via logs).
- Endpoint de auditoria: `GET /lembretes/consulta/{consultaId}`.
- Retentativa simples com backoff (5m, 15m, 60m).
- Encerramento limpo do scheduler com `@PreDestroy`.

## Como rodar
1. **Suba o RabbitMQ**:
   ```bash
   docker compose up -d
   ```
2. **Subir a aplicação**:
   ```bash
   mvn spring-boot:run
   ```
3. **Swagger UI**: http://localhost:8081/swagger-ui/index.html

## Exemplo de evento
```json
{
  "tipo": "CONSULTA_CRIADA",
  "consultaId": "abc-123",
  "pacienteContato": "email:paciente@exemplo.com",
  "dataHoraConsulta": "2025-10-03T14:00:00-03:00"
}
```

## Auditoria
`GET /lembretes/consulta/abc-123`
