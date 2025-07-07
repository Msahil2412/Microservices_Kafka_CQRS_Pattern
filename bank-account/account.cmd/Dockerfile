# account.cmd/Dockerfile

# Use a base image that supports Java 21
FROM eclipse-temurin:21-jdk-jammy

# Argument to pick the JAR
ARG JAR_FILE=target/*.jar

# Copy the JAR to the container
COPY ${JAR_FILE} app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]