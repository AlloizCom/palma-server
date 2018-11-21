package com.alloiz.palma.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan(basePackages = {"com.alloiz.palma.server"})
public class ApplicationWebMvcConfig extends WebMvcConfigurerAdapter {
    String rootPath = System.getProperty("catalina.home");
    String[] PATH = {
            "classpath:/resources/",
            "classpath:/resources/assets/i18n/",
            "file:/" + rootPath + "/resources/",
            "file:/" + rootPath + "/resources/assets/i18n/"
    };

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations(PATH)
                .setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS))
                .resourceChain(false)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }

}
