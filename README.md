# Game Of Three

Game repo link : https://github.com/SainathYelamanchali/GameOfThree

This project is developed on Windows environment as event driven micro-service which enables to play game of three between two players.
After two players services are started , game can be initiated and continues to play till one of the player reached 1 and eventually is the winner!!!
The communication between two micro-services is done by Rabbit Mq messaging.

### Prerequisites

1. Eclipse/IntelliJ IDE  with java 8 compatible
2. Maven installed
3. JDK 8 installed
3. Erlang installed (required for Rabbit Mq)
4. Rabbit MQ server installed 
5. Git installed
6. Windows environment


## Getting Started
1. Start the rabbit mq service before starting the players

  check the status of rabbit mq : rabbitmqctl.bat status
  
  reference: https://www.rabbitmq.com/install-windows-manual.html
 
2. Checkout the code to local repo and build the application using maven command

go to pom.xml path of the project -> mvn clean install
 
### To Run the application from command line
 
go to pom.xml path of the project ->

To bring Player 1 instance:

mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8085,instanceId=player1

To bring Player 2 instance:

mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8086,instanceId=player2

server.port and instanceId are the run-time properties passed dynamically.

### To Run the application from IDE

1. Import code into local IDE as maven project
2. Run GameOfThreeApplication.java by changing the port number and instanceId given as program arguments
for two different players





### How to initiate the game?
 
 Hit the below url from specific player instance to get initiated


 Method : GET
 
 URL : http:// {hostname}: {port}/GameOfThree/init
 
## For example in localhost:
1.If player1 wants to start the game,
GET: http://localhost:8085/GameOfThree/init

2.If player2 wants to start the game
GET : http://localhost:8086/GameOfThree/init

  

## How to check the messages in Rabbit Mq server

Open the below url in browser:

http: //{hostname}:15672/

Credentials: guest/guest

## Built With

* [Spring boot - 2.0.8 RELEASE](https://docs.spring.io/spring-boot/docs/) - The Spring Boot framework
* [Maven](https://maven.apache.org/) - Dependency Management 
* [Erlang](https://www.erlang.org/downloads) - Erlang 
* [Rabbit Mq](https://www.rabbitmq.com/download.html) - Rabbit MQ server
* project tags https://github.com/SainathYelamanchali/GameOfThree

## Authors

* **Sainath Yelamanchali** - https://github.com/SainathYelamanchali/
