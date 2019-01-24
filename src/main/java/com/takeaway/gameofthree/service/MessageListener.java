package com.takeaway.gameofthree.service;

import com.takeaway.gameofthree.config.GameOfThreeConfig;
import com.takeaway.gameofthree.domain.QueueMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * This class listens to the queue, processes the message and re-piblishes modified value
 */
@Component
@RabbitListener(queues = "gameOfThree")
public class MessageListener {
    private Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);
    @Autowired
    private GameOfThreeService service;

    @Autowired
    private MessagePublisher publisher;

    @Autowired
    private GameOfThreeConfig config;

    @RabbitHandler
    public void receive(Serializable message) {

        try {
            if (message instanceof Message) {
                byte[] msg = ((Message) message).getBody();
                QueueMessage queueMessage = (QueueMessage) message;
                LOGGER.info("Received message from Queue :{} ", queueMessage);
                if (!queueMessage.getUuid().equals(service.getUuidForInstance())) {
                    long value = queueMessage.getValue();
                    doWorkAndPublish(value);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Exception in processing the message from queue :{}", e);
        }

    }

    /**
     * This method reads the input value and fetches the closest value divisible by 3.
     * Only publishes if modified value is not equal to 1
     *
     * @param oldValue
     * @return modifiedValue
     */
    private long doWorkAndPublish(long oldValue) {

        long modifiedValue = service.getModifiedNumber(oldValue);
        modifiedValue = modifiedValue / 3;
        if (modifiedValue != 1) {
            LOGGER.debug("Performed Game of Three - oldValue :{} & modifiedValue:{}", oldValue, modifiedValue);
            publisher.publish(modifiedValue);
        } else {
            LOGGER.info("Hurray Congratulations!! {} have won", config.getInstanceId());
        }
        return modifiedValue;

    }
}
