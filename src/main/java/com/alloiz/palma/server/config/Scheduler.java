package com.alloiz.palma.server.config;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *  Scheduler for room and book
 */

@Component
@EnableScheduling
public class Scheduler {

    private static final Logger LOGGER
            = Logger.getLogger(Scheduler.class);


    /**
     *  1 sec  = 1000 ms
     *  1 min  = 60000 ms
     *  1 hr   = 3600000 ms
     *  1 day  = 86400000 ms
     *  1 week = 604800000 ms
     */
    @Scheduled(initialDelay = 1000, fixedRate = 86400000)
    public void run() {
        LOGGER.warn(">>> Scheduler start");

        LOGGER.warn("-------------------");
    }
}
