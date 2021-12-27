FROM openjdk:11-jre-slim

WORKDIR opt/server
COPY ./target/request_service-0.0.1-SNAPSHOT.jar server.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "server.jar"]