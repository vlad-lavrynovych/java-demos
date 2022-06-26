FROM openjdk:11
ENV APP_HOME=/usr/reactor
WORKDIR $APP_HOME
COPY target/*.jar reactor.jar
EXPOSE 8083
CMD ["java", "-jar", "reactor.jar"]
