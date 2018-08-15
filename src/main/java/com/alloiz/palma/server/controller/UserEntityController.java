package com.alloiz.palma.server.controller;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

import com.alloiz.palma.server.dto.UserEntityDto;
import com.alloiz.palma.server.model.UserEntity;
import com.alloiz.palma.server.repository.UserEntityRepository;
import com.alloiz.palma.server.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserEntityController {

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("/find-all")
    private ResponseEntity<List<UserEntityDto>> findAll() {
        return ResponseEntity.ok(userEntityService.findAll().stream()
                .map(callback -> map(callback, UserEntityDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<UserEntityDto>> findAllAvailable() {
        return ResponseEntity.ok(userEntityService.findAllAvailable().stream()
                .map(callback -> map(callback, UserEntityDto.class)).collect(Collectors.toList()));
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
    private ResponseEntity<UserEntityDto> save(@RequestBody UserEntityDto callback) {
        return ResponseEntity
                .ok(map(userEntityService.save(map(callback, UserEntity.class)), UserEntityDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<UserEntityDto> update(@RequestBody UserEntityDto callback) {
        return ResponseEntity
                .ok(map(userEntityService.update(map(callback, UserEntity.class)), UserEntityDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userEntityService.delete(id));
    }
}
