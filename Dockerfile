# Use a base image like OpenJDK
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy your application jar and the logback.xml to the working directory
COPY target/product-service-0.0.1-SNAPSHOT.jar product-service.jar
#COPY src/main/resources/logback-spring.xml /logs/logback-spring.xml

# Command to run your application
CMD ["java", "-jar", "product-service.jar"]

#FROM openjdk:17-jdk-slim
#COPY target/product-service-0.0.1-SNAPSHOT.jar product-service.jar
#ENTRYPOINT ["java", "-jar", "product-service.jar"]
