FROM openjdk:17

WORKDIR /Analytics
COPY target/Analytics-0.0.1-SNAPSHOT.jar /Analytics/Analytics-0.0.1-SNAPSHOT.jar
EXPOSE 8084
CMD ["java", "-jar", "Analytics-0.0.1-SNAPSHOT.jar"]