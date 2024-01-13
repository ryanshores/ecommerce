FROM openjdk:17
VOLUME /tmp
EXPOSE 8080

# the JAR file path
ARG JAR_FILE=target/*.jar

# Copy the JAR file from the build context into the Docker image
COPY ${JAR_FILE} application.jar

ENTRYPOINT ["java","-jar", "/application.jar"]