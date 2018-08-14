package com.alloiz.palma.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@EnableJpaRepositories(basePackages = "com.alloiz.palma.server.repository")
@Component
public class Beans {
//    @Bean
//    FileBuild er fileBuilder() {
//        return new FileBuilder();
//    }
}
