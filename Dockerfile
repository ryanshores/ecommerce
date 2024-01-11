FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/ecommerce-0.0.1-SNAPSHOT.jar ecommerce.jar
ENTRYPOINT ["java","-jar", "/ecommerce.jar"]