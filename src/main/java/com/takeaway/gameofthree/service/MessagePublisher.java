package com.takeaway.gameofthree.service;

import com.takeaway.gameofthree.config.GameOfThreeConfig;
import com.takeaway.gameofthree.domain.QueueMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for publishing the message to queue
 */
@Component
public class MessagePublisher {

    private Logger LOGGER = LoggerFactory.getLogger(MessagePublisher.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topic;

    @Autowired
    private GameOfThreeService service;
    @Autowired
    private GameOfThreeConfig config;

    /**
     * This method handles publishing the messsage to queue
     *
     * @param input
     */
    public void publish(long input) {

        String playerUuid = service.getLocalInstanceUuid();
        QueueMessage message = QueueMessage.newInstance().setUuid(playerUuid).setValue(input);
        String routingKey = getRoutingKey();
        rabbitTemplate.convertAndSend(topic.getName(), routingKey, message);
        LOGGER.info("Published value : {} into routingKey :{}", input,routingKey);
    }


    private String getRoutingKey() {
        String routingKey;
        if (config.getInstanceId().equalsIgnoreCase(GameOfThreeConfig.PLAYER_1)) {
            routingKey = service.getUuidForInstance(GameOfThreeConfig.PLAYER_2);
        } else {
            routingKey = service.getUuidForInstance(GameOfThreeConfig.PLAYER_1);
        }
        return routingKey;
    }

    /**
     * This method acts as a proxy to generate random whole number
     *
     * @return
     */
    public long getRandomNumber() {
        return service.getRandomWholeNumber();
    }
}
