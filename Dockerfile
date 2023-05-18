# Fetching latest version of Java
FROM openjdk:21-slim

# Setting up work directory
WORKDIR ekaasaa-service

# Copy the jar file into our app
COPY ./target/ekaasaa-0.0.1-SNAPSHOT.jar ekaasaa-service.jar

# Exposing port 8082
EXPOSE 8082

# Starting the application
CMD ["java", "-Dspring.profiles.active=docker", "-jar", "ekaasaa-service.jar"]
