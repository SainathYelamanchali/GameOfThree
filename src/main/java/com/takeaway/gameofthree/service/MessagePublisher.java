package com.takeaway.gameofthree.service;

import com.takeaway.gameofthree.domain.QueueMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
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
    private Queue queue;

    @Autowired
    private GameOfThreeService service;


    /**
     * This method handles publishing the messsage to queue
     * @param input
     */
    public void publish(long input) {

        String playerUuid = service.getUuidForInstance();

        QueueMessage message = QueueMessage.newInstance().setUuid(playerUuid).setValue(input);
        rabbitTemplate.convertAndSend(queue.getName(), message);
        LOGGER.info("Published value : {} into queue", input);
    }

    /**
     * This method acts as a proxy to generate random whole number
     * @return
     */
    public long getRandomNumber() {
        return service.getRandomWholeNumber();
    }
}
