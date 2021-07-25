# Github user data gatherer - Reactive, Spring, Redis

GitHub user data gatherer implemented in reactive way with Spring, Reactive Redis and WebFlux. It is example of usage
reactive programming with Spring Webflux.

The application download data of multiple github users, calculate scoring for each of the user **(6 / followers_number
* (2 + public_repos))** and return json list with user data in the scoring order. Each login request occurrence is saved
to Redis (embedded) database. It is possible to check number of requests for concrete logins only.

## Live demo

//TODO

## API

//TODO

**Bear in mind that github limit api access rate**

## Technologies used:

- Java 16
- Spring Boot
- Spring Webflux
- Redis (Embedded to simplify project setup)
- Swagger
- Gradle
- Netty

## Local run

It is possible to run application using some Java IDE such as **Intelij** or **Eclipse**. These IDEs cna help you with
easier gradle servicing.

Console approach (In project directory):

```bash
./gradlew build 

java -Dserver.port=8080 $JAVA_OPTS -jar build/libs/*.jar
```

Design principles presented:

- Reactive programming
- Contexts separation
- Package scope
- TDD
- ...


