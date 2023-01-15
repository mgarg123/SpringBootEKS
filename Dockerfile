FROM openjdk:8-alpine
COPY target/FirstApp-0.0.1-SNAPSHOT.jar application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","application.jar"]

