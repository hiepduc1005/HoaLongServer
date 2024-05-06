# Sử dụng hình ảnh OpenJDK Java 11 làm base image
FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:19
COPY --from=build /target/server-0.0.1-SNAPSHOT.jar server-0.0.1-SNAPSHOT.jar

EXPOSE 8080
CMD ["java", "-jar", "server-0.0.1-SNAPSHOT.jar"]
