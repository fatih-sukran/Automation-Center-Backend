# Use the official OpenJDK 11 base image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy only the necessary files for dependency resolution
COPY build.gradle settings.gradle /app/

# Copy the rest of the project
COPY . .

# Build the Gradle project to download dependencies
RUN ./gradlew build --no-daemon -x test

# Set the working directory to the location of the Spring Boot JAR file
WORKDIR /app/rest-api/build/libs

# Expose the port that your Spring Boot application will run on
EXPOSE 8080

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "rest-api-0.0.1-SNAPSHOT.jar"]
