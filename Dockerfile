FROM maven:3.9-openjdk-21-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/car_rental-0.0.1.jar app_rentACar.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app_rentACar.jar"]