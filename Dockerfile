FROM maven:3.9.7-eclipse-temurin-17-alpine

EXPOSE 8000

WORKDIR ./backend

COPY . .

RUN mvn clean install

RUN mvn package -DskipTests

CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]