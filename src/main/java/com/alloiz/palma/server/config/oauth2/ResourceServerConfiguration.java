package com.alloiz.palma.server.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import static com.alloiz.palma.server.config.oauth2.AuthorizationServerConfiguration.RESOURCE_ID;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/oauth/token").permitAll()

//                // AMENITY
//                .antMatchers(HttpMethod.POST, "/amenity/**").hasAnyAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/amenity/**").hasAnyAuthority("ADMIN")
//
//                // BOOK
//                .antMatchers(HttpMethod.POST, "//**").authenticated()
//                .antMatchers(HttpMethod.DELETE, "//**").authenticated()
//
//                // BOOK_COUNTER
//                .antMatchers(HttpMethod.POST, "/counter/**").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/counter/**").authenticated()
//
//                // CALLBACK
//                .antMatchers(HttpMethod.POST, "/callback/**").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/callback/**").authenticated()
//
//                //CALLBACK-COUNTER
//                .antMatchers(HttpMethod.POST, "/callback-gcounter/**").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/callback-gcounter/**").authenticated()
//
//                // MAIN
//                .antMatchers(HttpMethod.POST, "/main/**").hasAnyAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/main/**").hasAnyAuthority("ADMIN")
//
//                //NEWS
//                .antMatchers(HttpMethod.POST, "/news/**").hasAnyAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/news/**").hasAnyAuthority("ADMIN")
//
//                //PROPOSAL
//                .antMatchers(HttpMethod.POST, "/proposal/**").hasAnyAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/proposal/**").hasAnyAuthority("ADMIN")
//
//                //ROOM
//                .antMatchers(HttpMethod.POST, "/room/**").hasAnyAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/room/**").hasAnyAuthority("ADMIN")
//
//                //SCHEDULE
//                .antMatchers(HttpMethod.POST, "/schedule/**").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/schedule/**").authenticated()
//
//                //SERVICE
//                .antMatchers(HttpMethod.POST, "/service/**").hasAnyAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/service/**").hasAnyAuthority("ADMIN")
//
//                //TARIFF
//                .antMatchers(HttpMethod.POST, "/schedule/**").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/schedule/**").authenticated()
//
//                //USER
//                .antMatchers(HttpMethod.POST, "/user/**").hasAnyAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/user/**").hasAnyAuthority("ADMIN")
//
//                .antMatchers(HttpMethod.POST, "/callback/save").permitAll()
//                .antMatchers(HttpMethod.GET, "/**").permitAll()
//
//                .anyRequest().permitAll();

                // AMENITY
                .antMatchers(HttpMethod.POST, "/amenity/**").access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/amenity/**").access("hasAuthority('ADMIN')")

                // BOOK
                .antMatchers(HttpMethod.POST, "//**").authenticated()
                .antMatchers(HttpMethod.DELETE, "//**").authenticated()

                // BOOK_COUNTER
                .antMatchers(HttpMethod.POST, "/counter/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/counter/**").authenticated()

                // CALLBACK
//                .antMatchers(HttpMethod.POST, "/callback/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/callback/**").authenticated()

                //CALLBACK-COUNTER
                .antMatchers(HttpMethod.POST, "/callback-gcounter/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/callback-gcounter/**").authenticated()

                // MAIN
                .antMatchers(HttpMethod.POST, "/main/**").access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/main/**").access("hasAuthority('ADMIN')")

                //NEWS
                .antMatchers(HttpMethod.POST, "/news/**").access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/news/**").access("hasAuthority('ADMIN')")

                //PROPOSAL
                .antMatchers(HttpMethod.POST, "/proposal/**").access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/proposal/**").access("hasAuthority('ADMIN')")

                //ROOM
//                .antMatchers(HttpMethod.POST, "/room/**").access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/room/**").access("hasAuthority('ADMIN')")

                //SCHEDULE
                .antMatchers(HttpMethod.POST, "/schedule/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/schedule/**").authenticated()

                //SERVICE
                .antMatchers(HttpMethod.POST, "/service/**").access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/service/**").access("hasAuthority('ADMIN')")

                //TARIFF
                .antMatchers(HttpMethod.POST, "/schedule/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/schedule/**").authenticated()

                //USER
                .antMatchers(HttpMethod.POST, "/user/**").access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/user/**").access("hasAuthority('ADMIN')")

                .antMatchers(HttpMethod.POST, "/callback/save").permitAll()
                .antMatchers(HttpMethod.POST, "/room/**").permitAll()
                .antMatchers(HttpMethod.POST, "/room/save").access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.POST, "/room/update").access("hasAuthority('ADMIN')")
//                .antMatchers(HttpMethod.POST, "/room/find-by-book-params-with-room-type").permitAll()

                .antMatchers(HttpMethod.GET, "/**").permitAll()

                .anyRequest().permitAll();
    }
}