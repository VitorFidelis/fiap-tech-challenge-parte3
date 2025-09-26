#!/bin/bash

# Nome da sua imagem
IMAGE_NAME="service-gateway"

# Arquivo que guarda a versão
VERSION_FILE=".version"

# Caminho do Dockerfile
DOCKERFILE_PATH="./gateway/Dockerfile"
CONTEXT_PATH="./gateway"

# Se o arquivo não existe, começa em 0
if [ ! -f $VERSION_FILE ]; then
  echo 0 > $VERSION_FILE
fi

# Lê a versão atual e incrementa
VERSION=$(cat $VERSION_FILE)
NEW_VERSION=$((VERSION + 1))
echo $NEW_VERSION > $VERSION_FILE

# Tag final da imagem
TAG="v$NEW_VERSION"

echo "🚀 Gerando imagem $IMAGE_NAME:$TAG"

# Build da imagem apontando para o Dockerfile dentro da pasta gateway
docker build -f $DOCKERFILE_PATH -t $IMAGE_NAME:$TAG -t $IMAGE_NAME:latest $CONTEXT_PATH

# Atualiza docker-compose.yml (mesma pasta que o build.sh)
sed -i "s|image: $IMAGE_NAME:.*|image: $IMAGE_NAME:$TAG|g" docker-compose.yml

# Sobe os containers (sem derrubar tudo)
docker compose up --build

echo "✅ Imagem $IMAGE_NAME:$TAG criada e aplicada!"
