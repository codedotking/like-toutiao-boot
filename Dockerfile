FROM java:17

WORKDIR application
ARG JAR_FILE=target/*.jar
# Copy the jar file from the build stage
COPY target/*.jar application.jar

ENV DB_USERNAME=""
ENV DB_PASSWORD=""
ENV DB_URL=""

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]