package com.alloiz.palma.server.config;

import com.alloiz.palma.server.service.BookCounterService;
import com.alloiz.palma.server.service.CallbackCounterService;
import com.alloiz.palma.server.service.TariffService;
import com.alloiz.palma.server.service.UserEntityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Default data creator
 */
@Component
public class DefaultDataCreator {

    private static final Logger LOGGER
            = Logger.getLogger(DefaultDataCreator.class);

    @Autowired
    private Environment environment;

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private BookCounterService bookCounterService;

    @Autowired
    private CallbackCounterService callbackCounterService;

    @Autowired
    private TariffService tariffService;

    /**
     * This method starts once after server was run,
     * and creates default data in the data base:
     * User, Moderator, Tariffs, Counter
     */
    @PostConstruct
    public void init(){
        LOGGER.warn("---Creating Admin/Moderator---");
        if (userEntityService.createDefaultUser()){
            LOGGER.warn(">>>---Admin/Moderator was created---");
        } else {
            LOGGER.warn(">>>---Admin/Moderator already exist---");
        }
        LOGGER.warn("----------------------------------");
        LOGGER.warn("---Creating Counter for Booking---");
        if (bookCounterService.createDefaultCounter()){
            LOGGER.warn(">>>---Counter was created---");
        } else {
            LOGGER.warn(">>>---Counter already exists---");
        }
        LOGGER.warn("----------------------------------");
        LOGGER.warn("---Creating Counter for Callbacks---");
        if (callbackCounterService.createDefaultCounter()){
            LOGGER.warn(">>>---Counter was created---");
        } else {
            LOGGER.warn(">>>---Counter already exists---");
        }
        LOGGER.warn("----------------------------------");
        LOGGER.warn("---Creating default Tariff---");
        LOGGER.warn("-----------------------------");
        if (tariffService.createDefaultTariffs(true)){
            LOGGER.warn(">>>---Default tariffs were created---");
        } else {
            LOGGER.warn(">>>---Default tariffs already exist---");
        }
        LOGGER.warn("----------------------------------");
    }
}
