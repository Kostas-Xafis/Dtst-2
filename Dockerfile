FROM maven:3.9.7-eclipse-temurin-17-alpine

EXPOSE 8000

RUN apk update && apk add curl

WORKDIR ./backend

COPY . .

RUN mvn clean install -q

RUN mvn package -DskipTests

CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]