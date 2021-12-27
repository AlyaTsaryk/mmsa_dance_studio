FROM openjdk:11-jre-slim

WORKDIR opt/server
COPY ./target/identity_service-0.0.1-SNAPSHOT.jar server.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "server.jar"]