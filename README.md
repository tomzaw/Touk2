## 1.How to compile/run.

    mvnw clean install - remove old build and rebuild project.
    mvnw spring-boot:run - run spring boot application.

## 2.How to run tests.

    mvnw test - run all unit tests.

## 3.Test REST API.
For convenience manual testing of api can be performed using swagger. Swagger UI is available at path "localhost:8080/swagger-ui.html".

## 4.In memory database.
The h2 database client is available at path "localhost:8080/h2-console".

    username - root.
    password - root.

## 5.Test script.
To show example business process run python file 'script.py' - it is to use only on newly initialized database after application start. 