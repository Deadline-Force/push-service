FROM openjdk:17-alpine

WORKDIR /backend

COPY ./target/backend.jar /backend

EXPOSE 8080

ENTRYPOINT java -jar backend.jar