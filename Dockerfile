# Stage 1: build
FROM eclipse-temurin:21 AS build
WORKDIR /app
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src
# Build the jar (skip tests if you want)
RUN ./mvnw -B -DskipTests clean package

# Stage 2: run
FROM eclipse-temurin:21-jre
WORKDIR /app
# copia el jar del stage build
COPY --from=build /app/target/*.jar app.jar
# puerto expuesto por la app
EXPOSE 8080
# comando para iniciar la app
ENTRYPOINT ["java","-jar","/app/app.jar"]