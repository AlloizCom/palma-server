package com.alloiz.palma.server.controller;


import com.alloiz.palma.server.dto.CallbackByPage;
import com.alloiz.palma.server.dto.CallbackDto;
import com.alloiz.palma.server.model.Callback;
import com.alloiz.palma.server.service.CallbackService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/callback")
public class CallbackController {

    private static final Logger LOGGER = Logger.getLogger(CallbackController.class);

    @Autowired
    private CallbackService callbackService;

    @GetMapping("/find-all")
    private ResponseEntity<List<CallbackDto>> findAll() {
        return ResponseEntity.ok(callbackService.findAll().stream()
                .map(callback -> map(callback, CallbackDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<CallbackDto>> findAllAvailable() {
        return ResponseEntity.ok(callbackService.findAllAvailable().stream()
                .map(callback -> map(callback, CallbackDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<CallbackDto> findOneAvailale(@PathVariable Long id) {
        return ResponseEntity.ok(map(callbackService.findOneAvailable(id), CallbackDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<CallbackDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(map(callbackService.findOne(id), CallbackDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<CallbackDto> save(@RequestBody CallbackDto callback) {
        LOGGER.info("---------------------------Callback---------------------------");
        LOGGER.info(callback);
        LOGGER.info("---------------------------Callback---------------------------");
        return ResponseEntity
                .ok(map(callbackService.save(map(callback, Callback.class)), CallbackDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<CallbackDto> update(@RequestBody CallbackDto callback) {
        LOGGER.info("---------------------------Callback---------------------------");
        LOGGER.info(callback);
        LOGGER.info("---------------------------Callback---------------------------");
        return ResponseEntity
                .ok(map(callbackService.update(map(callback, Callback.class)), CallbackDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(callbackService.delete(id));
    }

    @GetMapping("/find-all-callbacks-by-page/{page}/{count}")
    private ResponseEntity<List<CallbackDto>> findAllPageable(@PathVariable Integer page,
                                                              @PathVariable Integer count) {
        return ResponseEntity.ok(callbackService
                .findAll(new PageRequest(page, count))
                .stream().map(schedule -> map(schedule, CallbackDto.class))
                .collect(toList()));
    }

    @GetMapping("/find-all-callbacks-by-page-available/{page}/{count}")
    private ResponseEntity<CallbackByPage> findAllPageableAvailable(@PathVariable Integer page,
                                                                    @PathVariable Integer count) {
        return ResponseEntity.ok(callbackService
                .findAllByAvailable(new PageRequest(page, count)));
    }

}
