# FROM openjdk:8-jdk-alpine
# FROM alpine:edge
FROM openjdk:8-jdk-slim
ARG JAR_FILE=target/backendoee-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]


# recorad cambiar el nombre de host en pom.xml
# mvn clean install -> compilacion
# java -jar target/backendoee-0.0.1-SNAPSHOT.jar -> ejecucion build

# NO hace falta, solo si se quiere la img individual
# docker build -t "backoee" .
# docker run --name backoee -p 8080:8080 backoee:latest