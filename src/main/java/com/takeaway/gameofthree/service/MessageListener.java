package com.takeaway.gameofthree.service;

import com.takeaway.gameofthree.config.GameOfThreeConfig;
import com.takeaway.gameofthree.domain.QueueMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * This class listens to the queue, processes the message and re-publishes modified value
 */
@Component
public class MessageListener {
    private Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);
    @Autowired
    private GameOfThreeService service;

    @Autowired
    private MessagePublisher publisher;

    @Autowired
    private GameOfThreeConfig config;


    @RabbitListener(queues = GameOfThreeConfig.QUEUE_NAME1)
    public void queue1(Serializable mg) {
        receive(mg);
    }

    @RabbitListener(queues = GameOfThreeConfig.QUEUE_NAME2)
    public void queue2(Serializable mg) {
        receive(mg);
    }


    private void receive(Serializable mg) {

        try {
            Message message = (Message) mg;
            ByteArrayInputStream in = new ByteArrayInputStream(message.getBody());
            ObjectInputStream is = new ObjectInputStream(in);
            QueueMessage queueMessage = (QueueMessage) is.readObject();
            String messageUuid = queueMessage.getUuid();
            String localInstanceUuid = service.getLocalInstanceUuid();
            if (!StringUtils.isEmpty(messageUuid) && !messageUuid.equals(localInstanceUuid)) {
                long value = queueMessage.getValue();
                doWorkAndPublish(value);
                LOGGER.info("Processed message from Queue :{} ", queueMessage);
            }

        } catch (Exception e) {
            LOGGER.error("Exception in processing the message from queue :", e);
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
