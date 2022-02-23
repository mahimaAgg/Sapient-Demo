FROM openjdk:8-jdk-alpine
COPY target/docker-weather-0.0.1-SNAPSHOT.jar weather-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/weather-0.0.1-SNAPSHOT.jar"]