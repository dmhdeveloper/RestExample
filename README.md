# RestExample
Simple rest app

# Building the application

mvn clean install

# Building the docker image

docker build -t rest .

# Running the docker image

docker run -p 8080:8080 -p 8787:8787 -p 9090:9090 -v {base directory of system}:/usr/src/mount --name rest -d rest

# Swagger URL

http://localhost:8080/swagger-ui.html

# Prometheus UI

http://locahost:9090