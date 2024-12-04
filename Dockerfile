FROM eclipse-temurin:17-jre
WORKDIR application
ARG JAR_FILE=target/*.jar



COPY target/*.jar application.jar


ENV username=""
ENV password=""
ENV url=""

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]