# Use the official OpenJDK 11 base image
FROM eclipse-temurin:21-jre-ubi9-minimal

# Set the working directory in the container
WORKDIR /app

# Build the Gradle project to download dependencies
RUN ./gradlew :lighthouse:build

# Set the working directory to the location of the Spring Boot JAR file
COPY . .

# Expose the port that your Spring Boot application will run on
EXPOSE 8080

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "rest-api-0.0.1-SNAPSHOT.jar"]
