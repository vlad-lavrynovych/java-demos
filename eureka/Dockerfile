FROM openjdk:11
ENV APP_HOME=/usr/eurekaserver
WORKDIR $APP_HOME
COPY target/*.jar eureka-server.jar
EXPOSE 8761
CMD ["java", "-jar", "eureka-server.jar"]
