FROM openjdk:8-jre-alpine
MAINTAINER east196
ADD target/spring-boot-admin-server.jar app.jar
EXPOSE 9600
ENTRYPOINT ["java","-jar","app.jar"]



