# Use the official OpenJDK 17 image as the base image
FROM gradle:7.6.3-jdk17-alpine

# Set the working directory inside the container
RUN mkdir -p /app
WORKDIR /app

# Copy the Gradle wrapper and build files to the container
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
# Copy the entire source code to the container
COPY src src

# Build the project with Gradle
RUN gradle build

# Expose the port your application will run on (if needed)
EXPOSE 8081

# Command to run your application
CMD ["java", "-jar", "build/libs/basicTests-0.0.1-SNAPSHOT.jar"]
