# Stage 1: build
# Usamos una imagen oficial de Maven que ya incluye Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos solo el pom.xml primero para aprovechar la caché de capas
COPY pom.xml .
# Descargamos dependencias (opcional, pero acelera builds futuros)
RUN mvn dependency:go-offline

# Copiamos el código fuente
COPY src src

RUN mvn -B -DskipTests clean package

# Stage 2: run
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]