# =========================
# Stage 1: Build (Maven + JDK 21)
# =========================
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o POM pai e os POMs dos módulos para cachear dependências
COPY pom.xml .
COPY agendamento/pom.xml ./agendamento/
COPY notification/pom.xml ./notification/

# Baixa dependências em cache (offline)
RUN mvn -q -DskipTests dependency:go-offline

# Copia o código-fonte do módulo notification
COPY notification/src ./notification/src

# Build apenas do módulo notification (puxa dependências necessárias com -am)
RUN mvn -q -DskipTests -pl notification -am clean package

# =========================
# Stage 2: Runtime (JRE 21)
# =========================
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o jar do módulo notification
# Ajuste o padrão abaixo se o seu artifactId/version forem diferentes
COPY --from=build /app/notification/target/*.jar app.jar

# A porta interna segue seu application.properties (server.port=8081)
EXPOSE 8081

# (Opcional) Tuning de memória em containers
# ENV JAVA_TOOL_OPTIONS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

ENTRYPOINT ["java","-jar","/app/app.jar"]