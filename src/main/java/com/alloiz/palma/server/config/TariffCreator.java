package com.alloiz.palma.server.config;

import com.alloiz.palma.server.service.TariffService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class TariffCreator {
    private static final Logger LOG
            = Logger.getLogger(TariffCreator.class);
    @Autowired
    private Environment environment;

    @Autowired
    private TariffService tariffService;

    @PostConstruct
    public void init() {
        LOG.warn("---Creating default tariff---");
        tariffService.createDefaultTariffs(true);
        LOG.warn("---Creating default tariff---");
    }
}
