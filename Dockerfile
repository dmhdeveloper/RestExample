FROM openjdk:11

RUN mkdir /usr/src/restapp

RUN mkdir /usr/src/mount

WORKDIR /usr/src/restapp/

VOLUME /usr/src/mount

EXPOSE 8787
EXPOSE 8080

ARG JAR_FILE

COPY ${JAR_FILE} /usr/src/restapp/app.jar

CMD ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n", "app.jar"]
