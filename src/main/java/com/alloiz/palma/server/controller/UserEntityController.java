package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.UserEntityDto;
import com.alloiz.palma.server.model.UserEntity;
import com.alloiz.palma.server.service.UserEntityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

@EnableResourceServer
@RestController
@RequestMapping("/user")
public class UserEntityController {

    private static final Logger LOGGER = Logger.getLogger(UserEntityController.class);


    @Autowired
    private UserEntityService userEntityService;

    @GetMapping
    private ResponseEntity<UserEntityDto> getUser(Principal principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info(authentication == null);
        LOGGER.info(principal == null);
        if (authentication == null)
            return ResponseEntity.ok(null);
//        if (principal == null)
//            return ResponseEntity.ok(null);
        LOGGER.info(authentication.toString());
        LOGGER.info(authentication.getName());
        LOGGER.info(userEntityService.findByLogin(authentication.getName()));
        return ResponseEntity.ok(map(userEntityService.findByLogin(authentication.getName()), UserEntityDto.class));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<UserEntityDto>> findAll() {
        return ResponseEntity.ok(userEntityService.findAll().stream()
                .map(userEntity -> map(userEntity, UserEntityDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<UserEntityDto>> findAllAvailable() {
        return ResponseEntity.ok(userEntityService.findAllAvailable().stream()
                .map(userEntity -> map(userEntity, UserEntityDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<UserEntityDto> findOneAvailale(@PathVariable Long id) {
        return ResponseEntity.ok(map(userEntityService.findOneAvailable(id), UserEntityDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<UserEntityDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(map(userEntityService.findOne(id), UserEntityDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<UserEntityDto> save(@RequestBody UserEntityDto user) {
        LOGGER.info("---------------------------User---------------------------");
        LOGGER.info(user);
        LOGGER.info("---------------------------User---------------------------");
        return ResponseEntity
                .ok(map(userEntityService.save(map(user, UserEntity.class)), UserEntityDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<UserEntityDto> update(@RequestBody UserEntityDto user) {
        LOGGER.info("---------------------------User---------------------------");
        LOGGER.info(user);
        LOGGER.info("---------------------------User---------------------------");
        return ResponseEntity
                .ok(map(userEntityService.update(map(user, UserEntity.class)), UserEntityDto.class));
    }

    @PostMapping("/update-password")
    private ResponseEntity<UserEntityDto> updatePassword(@RequestBody UserEntityDto user) {
        LOGGER.info("---------------------------User---------------------------");
        LOGGER.info(user);
        LOGGER.info("---------------------------User---------------------------");
        return ResponseEntity
                .ok(map(userEntityService.updatePassword(map(user, UserEntity.class)), UserEntityDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userEntityService.delete(id));
    }
}
