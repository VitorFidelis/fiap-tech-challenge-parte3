# Stage 1: Build do JAR com Maven + JDK 21
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar pom.xml e baixar dependências antes de copiar o código todo (cache eficiente)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar código-fonte e gerar o JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiar o JAR gerado do stage anterior
COPY --from=build /app/target/gateway-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]
