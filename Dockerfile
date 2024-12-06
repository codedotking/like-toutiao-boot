FROM bitnami/java:17
# 设置工作目录 cd
WORKDIR /app
# Copy the jar file from the build stage
COPY target/*.jar application.jar
COPY src/main/resources/application.yml conf/application.yml
# 暴露的端口
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar", "--spring.config.location=/app/conf/"]