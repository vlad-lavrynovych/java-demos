FROM openjdk:11
ENV APP_HOME=/usr/feign
WORKDIR $APP_HOME
COPY target/*.jar feign.jar
EXPOSE 8987
CMD ["java", "-jar", "feign.jar"]
