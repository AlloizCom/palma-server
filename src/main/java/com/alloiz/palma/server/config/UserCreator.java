package com.alloiz.palma.server.config;

import com.alloiz.palma.server.service.UserEntityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserCreator {

    private static final Logger LOGGER
            = Logger.getLogger(UserCreator.class);

    @Autowired
    private Environment environment;

    @Autowired
    private UserEntityService userEntityService;

    @PostConstruct
    public void init(){
        LOGGER.warn("---Creating Admin/Moderator---");
        if (userEntityService.createDefaultUser()){
            LOGGER.warn(">>>---Admin/Moderator was created---");
        }
        LOGGER.warn("---Creating Admin/Moderator---");
    }
}
