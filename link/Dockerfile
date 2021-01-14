FROM openjdk:8-jre-slim

ADD build/resources/main/application.properties /usr/local/link/application.properties
ADD build/libs/link-0.0.1.jar /usr/local/link/link-0.0.1.jar

EXPOSE 8088

CMD ["java","-Dspring.config.location=/usr/local/link/application.properties", "-jar", "/usr/local/link/link-0.0.1.jar"]