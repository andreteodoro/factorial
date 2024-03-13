FROM openjdk:17 as build
WORKDIR /workspace/app

COPY /factorial-code-challenge/mvnw .
COPY /factorial-code-challenge/.mvn .mvn
COPY /factorial-code-challenge/pom.xml .
COPY /factorial-code-challenge/src src

RUN ./mvnw install -DskipTests

FROM openjdk:17
VOLUME /tmp
COPY --from=build /workspace/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

