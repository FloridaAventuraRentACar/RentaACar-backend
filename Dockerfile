FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/car_rental-0.0.1.jar app_rentACar.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app_rentACar.jar"]