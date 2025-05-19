# Use a specific OpenJDK base image with a JRE (or JDK if needed for build tools)
FROM eclipse-temurin:17-jre-alpine

# Update package lists
RUN apk update

# Install the JDK (if you need build tools like Gradle inside the container)
# RUN apk add --no-cache openjdk17-jdk

# Or just the JRE (if your JAR is pre-built)
RUN apk add --no-cache openjdk17-jre

# Set the JAVA_HOME environment variable within the container
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY build/libs/your-project-name-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application will run on (default is 8080)
EXPOSE 8080

# Define the command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]