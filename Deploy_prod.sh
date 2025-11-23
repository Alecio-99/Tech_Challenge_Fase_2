#!/bin/bash

echo "🚀 Iniciando processo de deploy do Tech Challenge Fase 2..."

echo "📌 Passo 1 → Limpando e empacotando aplicação com Maven..."
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
  echo "❌ Erro ao empacotar com Maven! Abortando..."
  exit 1
fi

echo "🧹 Passo 2 → Removendo containers antigos..."
docker-compose down --remove-orphans

echo "🧼 Passo 3 → Limpando cache de build e imagens órfãs..."
docker builder prune -a -f
docker image prune -a -f
docker system prune -a -f

echo "🐳 Passo 4 → Construindo e iniciando containers (sem cache)..."
docker-compose build --no-cache
docker-compose up -d

if [ $? -eq 0 ]; then
    echo "💚 Deploy realizado com sucesso!"
    echo "📌 Acesse o Swagger: http://localhost:8080/swagger-ui/index.html"
else
    echo "❌ Falha ao iniciar containers!"
    exit 1
fi

echo "📌 Logs da API (CTRL+C para sair)"
docker logs -f techchallenge-api
