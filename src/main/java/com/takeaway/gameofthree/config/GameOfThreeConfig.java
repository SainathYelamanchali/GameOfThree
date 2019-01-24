package com.takeaway.gameofthree.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class handles all the configurations related to Game
 */
@Configuration

public class GameOfThreeConfig {

    public static final String PLAYER1_UUID = "d039329f-db80-4ddb-8846-5ca35f2688f5";
    public static final String PLAYER2_UUID = "1f3490c8-1f99-11e9-ab14-d663bd873d93";
    public static final String QUEUE_NAME1 = "gameOfThreeQueue1";
    public static final String QUEUE_NAME2 = "gameOfThreeQueue2";
    public static final String PLAYER_1 = "player1";
    public static final String PLAYER_2 = "player2";

    @Value("${instanceId:player1}")
    private String instanceId;

    public String getInstanceId() {
        return instanceId;
    }

    @Bean
    public TopicExchange gameOfThreeTopic() {
        return new TopicExchange("gameOfThreeExchange");
    }

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_NAME1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_NAME2);
    }


    @Bean
    public Binding queue1Binding(TopicExchange topic,
                                 Queue queue1) {
        return BindingBuilder.bind(queue1)
                .to(topic)
                .with(PLAYER2_UUID);
    }

    @Bean
    public Binding queue2Binding(TopicExchange topic,
                                 Queue queue2) {
        return BindingBuilder.bind(queue2)
                .to(topic)
                .with(PLAYER1_UUID);
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
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }

}
