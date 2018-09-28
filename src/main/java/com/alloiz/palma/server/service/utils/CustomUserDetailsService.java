package com.alloiz.palma.server.service.utils;

import com.alloiz.palma.server.repository.UserEntityRepository;
import com.alloiz.palma.server.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        if (ofNullable(userDetails = userEntityRepository.findByLogin(s)).isPresent()) {
            return userDetails;
        }
        return userDetails;
    }
}
