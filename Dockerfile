FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/car_rental-0.0.1.jar
COPY ${JAR_FILE} app_rentACar.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app_rentACar.jar"]