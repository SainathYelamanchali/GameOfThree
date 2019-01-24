package com.takeaway.gameofthree.api;

import com.takeaway.gameofthree.service.MessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * THis controller handles all Rest APIs required by the game
 */
@RestController
@RequestMapping("/GameOfThree")
public class GameOfThreeController {

    private Logger LOGGER = LoggerFactory.getLogger(GameOfThreeController.class);

    @Autowired
    private MessagePublisher messagePublisher;

    /**
     * Heart Beat API for this micro-service
     */
    @GetMapping(value = "/health")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void heartBeat() {
        // health check method , left empty
        System.out.println("Health check");
    }

    /**
     * This API handles the entry point of the game to kick-off
     *
     * @return
     */
    @GetMapping(value = "/init")
    public String initGame() {
        try {
            long randomNumber = messagePublisher.getRandomNumber();
            messagePublisher.publish(randomNumber);
            return "Successfully Published";
        } catch (Exception e) {
            LOGGER.error("Exception in publishing message:", e);
            return "Publish Failed";
        }
    }
}
