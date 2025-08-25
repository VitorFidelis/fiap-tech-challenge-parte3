#!/bin/bash
set -e

# Carrega variáveis do .env
source .env

echo "🔎 Testando containers..."

# Lista containers que interessam
docker ps --format "table {{.Names}}\t{{.Status}}" | grep -E "db-agendamento|db-notificacao|db-historico|rabbitmq"

# Função para testar Postgres
test_postgres() {
  local container=$1
  local db=$2
  echo "⏳ Testando conexão ao banco $db no container $container..."
  docker exec -i $container psql -U $POSTGRES_USER -d $db -c "SELECT version();" >/dev/null 2>&1 && \
  echo "✅ $db OK" || echo "❌ Falha ao conectar $db"
}

# Testa os 3 bancos
test_postgres db-agendamento agendamento_db
test_postgres db-notificacao notificacao_db
test_postgres db-historico historico_db

# Testa RabbitMQ via API de health
echo "⏳ Testando RabbitMQ..."
curl -u $RABBITMQ_USER:$RABBITMQ_PASSWORD -s http://localhost:15672/api/overview >/dev/null && \
echo "✅ RabbitMQ OK (painel acessível em http://localhost:15672)" || echo "❌ Falha no RabbitMQ"

echo "🚀 Testes finalizados!"
