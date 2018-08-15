package com.alloiz.palma.server.config;

import com.alloiz.palma.server.service.utils.FileBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@EnableJpaRepositories(basePackages = "com.alloiz.palma.server.repository")
@Component
public class Beans {
    @Bean
    FileBuilder fileBuilder() {
        return new FileBuilder();
    }

}
