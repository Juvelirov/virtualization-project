FROM openjdk:21-jdk-slim
LABEL authors="alivka"

WORKDIR /virtualization-project

COPY target/virtualization-project-0.0.1-SNAPSHOT.jar virtualization-project.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "virtualization-project.jar"]