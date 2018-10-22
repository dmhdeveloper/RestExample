FROM openjdk:8

RUN mkdir /usr/src/restapp

RUN mkdir /usr/src/mount

RUN mkdir /usr/src/prometheus

RUN wget https://github.com/prometheus/prometheus/releases/download/v2.4.3/prometheus-2.4.3.linux-amd64.tar.gz

RUN tar -xvf prometheus-2.4.3.linux-amd64.tar.gz -C /usr/src/prometheus

RUN rm -f prometheus-2.4.3.linux-amd64.tar.gz

COPY config/setup-prometheus.sh .

RUN ./setup-prometheus.sh

WORKDIR /usr/src/restapp/

VOLUME /usr/src/mount

EXPOSE 8787
EXPOSE 8080
EXPOSE 9090

COPY rest-core/target/rest-core-1.0-SNAPSHOT.jar /usr/src/restapp/

CMD ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n", "rest-core-1.0-SNAPSHOT.jar"]
CMD ["/usr/src/prometheus/prometheus-2.4.3.linux-amd64/./prometheus", "--config.file=/usr/src/prometheus/prometheus.yml"]
