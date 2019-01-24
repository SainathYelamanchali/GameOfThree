# Game Of Three

Game repo link : https://github.com/SainathYelamanchali/GameOfThree

This project is developed as micor-service to play game of three between two players
After two players are started , game can be initiated and continues to play till one of the player reached 1.
The communication between two micro-services is done by Rabbit Mq.

### Prerequisites

1. Eclipse/IntelliJ IDE  with java 8 compatible
2. Maven installed
3. Erlang installed
4. Rabbit MQ server based on OS installed 

## Getting Started

Checkout this project to your local repo and build the application

go to pom.xml path -> mvn clean install

### To Run the application from command line
 
go to pom.xml path -> mvn spring-boot:run

### To Run the application from eclipse

1. Import code into local IDE
2. Run GameOfThreeApplication.java by changing the port number for two different players

### Unit tests

SpringRunner Junits are written for Services,Factory and repository classes .

### Demo 

   #### Welcome to Multi Player Game ###

  Select below options to proceed.
  
   * 1.Create a character.
   * 2.Do you already know your registered Character details?
   * 3.Exit

  ...........

## Built With

* [Spring boot - 2.0.8 RELEASE](https://docs.spring.io/spring-boot/docs/) - The Spring Boot framework
* [Maven](https://maven.apache.org/) - Dependency Management 
* [Erlang](https://www.erlang.org/downloads) - Erlang 
* [Rabbit Mq] (https://www.rabbitmq.com/download.html) - Rabbit MQ server
* project tags https://github.com/SainathYelamanchali/GameOfThree

## Authors

* **Sainath Yelamanchali** - https://github.com/SainathYelamanchali/