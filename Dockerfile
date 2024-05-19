FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/calmomilla-api-0.0.1-SNAPSHOT.jar calmomilla-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar","calmomilla-api-0.0.1-SNAPSHOT.jar"]