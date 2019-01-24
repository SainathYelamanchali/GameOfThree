package com.takeaway.gameofthree.service;

import com.takeaway.gameofthree.config.GameOfThreeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This service handles the generation of random numbers and instanceId's UUIDSs
 */
@Service
public class GameOfThreeService {
    private Logger LOGGER = LoggerFactory.getLogger(GameOfThreeService.class);
    public static long GAME_OF_THREE = 3;

    private static Map<String, String> instancesMap;

    @Autowired
    private GameOfThreeConfig config;

    static {
        instancesMap = new HashMap<>();
        instancesMap.put(GameOfThreeConfig.PLAYER_1, GameOfThreeConfig.PLAYER1_UUID);
        instancesMap.put(GameOfThreeConfig.PLAYER_2, GameOfThreeConfig.PLAYER2_UUID);
    }

    public String getLocalInstanceUuid() {
        String instanceId = config.getInstanceId();
        return getUuidForInstance(instanceId);
    }

    public String getUuidForInstance(String instanceId) {
        if (instanceId == null) {
            throw new IllegalArgumentException("Invalid instanceId passed");
        }
        return instancesMap.get(instanceId);
    }

    public long getRandomWholeNumber() {

        return ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);

    }

    public long getModifiedNumber(long input) {

        long quotient = input / GAME_OF_THREE;

        long firstClosest = GAME_OF_THREE * quotient;

        long secondClosest = (input * GAME_OF_THREE) > 0 ? (GAME_OF_THREE * (quotient + 1)) : (GAME_OF_THREE * (quotient - 1));

        LOGGER.debug("Operation Performed for input:{}: and integer:{}", input, secondClosest - firstClosest);
        if (Math.abs(input - firstClosest) < Math.abs(input - secondClosest))
            return firstClosest;


        return secondClosest;

    }


}
