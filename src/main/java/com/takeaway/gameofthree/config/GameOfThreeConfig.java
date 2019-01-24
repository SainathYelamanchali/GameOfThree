package com.takeaway.gameofthree.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * This class handles all the configurations related to Game
 */
@Configuration
public class GameOfThreeConfig {

    public static final String PLAYER1_UUID = UUID.randomUUID().toString();
    public static final String PLAYER2_UUID = UUID.randomUUID().toString();

    @Value("instanceId")
    private String instanceId;

    public String getInstanceId() {
        return instanceId;
    }

    @Bean
    public Queue gameOfThree() {
        return new Queue("gameOfThree");
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest"); //externalize
        connectionFactory.setPort(5672);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
}
