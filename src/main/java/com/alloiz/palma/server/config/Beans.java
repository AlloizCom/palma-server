package com.alloiz.palma.server.config;

import com.alloiz.palma.server.service.utils.FileBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@EnableJpaRepositories(basePackages = "com.alloiz.palma.server.repository")
@Component
public class Beans {
    @Bean
    FileBuilder fileBuilder() {
        return new FileBuilder();
    }

    /**
     * Sets Bcrypt encoder to be used in application.
     *
     * @return PAsswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
