#FROM ubuntu:latest
#LABEL authors="rehanasheikh"
#
#ENTRYPOINT ["top", "-b"]
FROM amazoncorretto:21-alpine3.17-jdk
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
