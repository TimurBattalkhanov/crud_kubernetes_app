FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/crud_kuber_app-1.0.jar /app
CMD ["java", "-jar", "crud_kuber_app-1.0.jar"]