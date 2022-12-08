# spring-kafka-consumer
> Kafka consumer using Spring boot and Spring-kafka dependency.
> 
> _Coded by [atromilen](https://github.com/atromilen)_

## About technology

**Spring boot**

Responsible for creating a Stand-alone application that just run in any environment, using all Spring framework benefits.

**Spring-kafka**

This Spring module allow us to interact with Kafka broker, providing us a **Template** as a high-level abstraction for send
or consume messages to Kafka topics. Also, provides support for Message-driven POJOs with **@KafkaListener** annotations and
a Listener container. Features and documentation can be found [here](https://spring.io/projects/spring-kafka).

**Docker compose**

The project is based on docker-compose to starts the infrastructure needed by this project. Docker compose is managing
Zookeeper and Kafka services.

**Makefile**

All the commands related to docker services initialization, Springboot running or gradle task were automated using
makefiles. This is a convenience and easy way to start all the infrastructure and build tasks entering only one command
instead to follow several steps to run the application. You can find documentation
related to makefiles [here](https://makefiletutorial.com/).

## Prerequisites
1. **Java 11** is the base language used to code this application and you will need to install the jdk 11 to run the app.
2. **Docker** is need to start up the app's related services with docker-compose. Can be found 
[here](https://docs.docker.com/get-docker/).
3. **Make** is required to run the makefile that builds and executes the application. This may be part of some Unix based
OS or can be installed through package managers such as apt-get or be part of development tools like Xcode in mac osx. 
In Windows OS you can install it through some package manager like [Scoop](https://scoop.sh/) or 
[Chocolatey](https://chocolatey.org/).

## Getting Started

1. Once you've cloned the project, it will be necessary start docker service containers (postgres SQL and pgClient) and
execute the initial database migration over _Postgre SQL_ using _Flyway_. For this, enter the following command:
    ```bash
    make build
    ```

2. To start the Spring boot application, enter:
    ```bash
    make start
    ```
    Note: always it's possible to start a Spring Boot application directly in your preferred IDE or executing _**./gradlew bootRun**_

**Optional commands**

* If you need to stop the Spring Boot Application, open another terminal window and enter:
    ```bash
    make stop
    ```
Consider that the make stop also will stop the container services.

* If you want to stop the service containers and erase containers from your local docker, execute the next command:
    ```bash
    make clean
    ```

## Changes & Features History

### [Initial Commit](https://github.com/atromilen/spring-kafka-consumer/commit/255574afda957476f042289ac770ffaf144530ef)
Added docker-compose services definitions, gradle dependencies and makefile to automate application tasks such as build, start,
stop and clean.
