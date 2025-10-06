# Stage 1: Build do JAR com Maven + JDK 21
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar POM pai primeiro
COPY pom.xml .
# Copiar POM do módulo agendamento
COPY agendamento/pom.xml ./agendamento/
COPY notification/pom.xml ./notification/

# Baixar dependências
RUN mvn dependency:go-offline -B

# Copiar código-fonte do módulo agendamento
COPY agendamento/src ./agendamento/src

# Construir apenas o módulo agendamento
RUN mvn clean package -pl agendamento -am -DskipTests -B

# Stage 2: Runtime
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiar o JAR gerado do stage anterior
COPY --from=build /app/agendamento/target/agendamento-*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]