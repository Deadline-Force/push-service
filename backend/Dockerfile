FROM maven:3.9.6-amazoncorretto-17-debian

WORKDIR /backend

COPY . .

RUN mvn clean package

EXPOSE 8080

ENTRYPOINT java -jar ./target/backend.jar
