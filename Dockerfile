
FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE="target/room-service-0.0.1-SNAPSHOT.jar"

WORKDIR /opt/room-service

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/room-service/app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","app.jar"]