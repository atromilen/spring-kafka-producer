# spring-kafka-producer
> Kafka producer using Spring boot and Spring-kafka dependency.
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

The project is based on docker-compose to start the infrastructure required by this project. The services for this 
project managed by docker compose are: 
- [Zookeeper](https://zookeeper.apache.org/)
- [Kafka](https://www.confluent.io/what-is-apache-kafka/?utm_medium=sem&utm_source=google&utm_campaign=ch.sem_br.brand_tp.prs_tgt.confluent-brand_mt.xct_rgn.latam_lng.eng_dv.all_con.confluent-kafka-general&utm_term=confluent%20kafka&creative=&device=c&placement=&gclid=CjwKCAiA-dCcBhBQEiwAeWidtRN_W79Qa5fTtZF73BZqBUuyFk3cFhtr9Dswl0rOsoPgPGU6czD52BoCsGMQAvD_BwE)
- [AKHQ](https://akhq.io/)
- [Schema Registry](https://developer.confluent.io/learn-kafka/apache-kafka/schema-registry/)
- [Kafka Connect](https://developer.confluent.io/learn-kafka/apache-kafka/kafka-connect/)

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

### [Programmatic topic creation](https://github.com/atromilen/spring-kafka-consumer/pull/2)
Added the feature for the initial topic creation when the application starts, setting the Spring beans required and
adding the config in **application.yml** for boostrap-servers, topic name, number of partitions and replicas.<br/>
By other hand, port 29092 of kafka service is now exposed to listening from localhost (required for the application).

### [Fix: change in project description](https://github.com/atromilen/spring-kafka-producer/pull/3/files)
Changed project description to **producer** instad of consumer. Added in README, detail of docker services managed by 
docker-compose.

### [Kafka Producer implementation](https://github.com/atromilen/spring-kafka-producer/pull/4)
- Implementation of the Controller to receive messages to be sent to Kafka via the Service class
- Integrated test for service layer. <br/>
- Properties handling was encapsulated into the new class **ConfigProperties**, in order to inject it when it will be 
necessary. <br/>
- Added test profile to separate properties for embedded kafka broker in test scope. <br/>
- Improved the documentation about the kafka properties specified in application.yml adding a new **MANIFEST-INFO** json file.

### [Validations and Improvements](https://github.com/atromilen/spring-kafka-producer/pull/5)
In this commit was added validation for the REST API payload, a controller advice to handle validations and the
Unit Test to check REST API responses with status 400 in validation errors and status 201 in success.<br/>
Some syntactic sugar was added: replaced params constructor by **lombok decorator @RequiredArgsConstructor** in controller, 
service and config beans.

### [Type mappings and refactoring](https://github.com/atromilen/spring-kafka-producer/pull/6)
- Added type_mappings to producer properties, to correspond alias "event" in the consumer configuration
- Packages renamed in order to give more readability
- Added 1 more deep level (added producer) into application properties. Also modified META_INF file.
