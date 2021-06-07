FROM openjdk:8-jre-alpine
MAINTAINER east196
ADD target/nacos-admin-server.jar /app/nacos-admin-server/nacos-admin-server.jar
EXPOSE 8084
ENTRYPOINT ["java","-Xms128m","-Xmx128m","-Djava.security.egd=file:/dev/./urandom",\
            "-jar","/app/nacos-admin-server/nacos-admin-server.jar"]



