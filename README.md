# Game Of Three

Game repo link : https://github.com/SainathYelamanchali/GameOfThree

This project is developed as event driven micro-service which enables to play game of three between two players
After two players services are started , game can be initiated and continues to play till one of the player reached 1.
The communication between two micro-services is done by Rabbit Mq messaging.

### Prerequisites

1. Eclipse/IntelliJ IDE  with java 8 compatible
2. Maven installed
3. Erlang installed (required for Rabbit Mq)
4. Rabbit MQ server installed 

## Getting Started

Checkout this project to your local repo and build the application

go to pom.xml path -> mvn clean install

### To Run the application from command line
 
go to pom.xml path ->

To bring Player 1 instance:
mvn spring-boot:run -Dspring.boot.run.arguments=--server.port=8085,instanceId=player1

To bring Player 2 instance:
mvn spring-boot:run -Dspring.boot.run.arguments=--server.port=8086,instanceId=player2


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
