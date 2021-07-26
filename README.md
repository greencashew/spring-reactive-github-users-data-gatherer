# Github user data gatherer - Reactive, Spring, Redis

GitHub user data gatherer implemented in reactive way with Spring, Reactive Redis and WebFlux. It is example of usage
reactive programming with Spring Webflux.

The application download data of multiple github users, calculate scoring for each of the user **(6 / followers_number * (2 + public_repos))** and return json list with user data in the scoring order. Each login request occurrence is saved
to Redis (embedded) database. It is possible to check number of requests for concrete logins only.

## Live demo

//TODO

## API

```bash

Documentation UI path: 
/swagger-ui/

Health check endpoint:
GET /actuator/health/

Get users data for logins:
GET /users/{logins}

Get request occurances for logins:
GET /users/requests/{logins}

```


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


Link to swagger documentation: http://localhost:8080/swagger-ui/


Design principles presented:

- Reactive programming
- Contexts separation
- Package scope
- TDD
- ...


