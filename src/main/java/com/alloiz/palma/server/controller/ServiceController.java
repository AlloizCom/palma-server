package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.ServiceFullDto;
import com.alloiz.palma.server.dto.ServiceShortDto;
import com.alloiz.palma.server.service.ServiceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private static final Logger LOGGER = Logger.getLogger(ServiceController.class);

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/find-all")
    private ResponseEntity<List<ServiceFullDto>> findAll() {
        return new ResponseEntity<>(serviceService.findAll().stream()
                .map(news -> map(news, ServiceFullDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<ServiceFullDto>> findAllAvailable() {
        return new ResponseEntity<>(serviceService.findAllAvailable().stream()
                .map(news -> map(news, ServiceFullDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<ServiceFullDto> findOneAvailale(@PathVariable Long id) {
        return new ResponseEntity<>(map(serviceService.findOneAvailable(id), ServiceFullDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<ServiceFullDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(serviceService.findOne(id), ServiceFullDto.class), HttpStatus.OK);
    }

//    @PostMapping("/save")
//    private ResponseEntity<ServiceFullDto> save(@RequestParam String serviceJson,
//                                                @RequestParam(required = false) MultipartFile multipartFile) {
//        LOGGER.info("---------------------------Service---------------------------");
//        LOGGER.info(serviceJson);
//        LOGGER.info("---------------------------Service---------------------------");
//        return ResponseEntity.ok(map(serviceService.save(serviceJson,multipartFile), ServiceFullDto.class));
//    }

    @PostMapping("/save")
    private ResponseEntity<ServiceFullDto> save(@RequestParam String serviceJson,
                                             @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------News---------------------------");
        LOGGER.info(serviceJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------News---------------------------");
        return ResponseEntity.ok(map(serviceService.save(serviceJson, multipartFile), ServiceFullDto.class));
    }

//    @PostMapping("/update")
//    private ResponseEntity<ServiceFullDto> update(@RequestParam String serviceJson) {
//        LOGGER.info("---------------------------Service---------------------------");
//        LOGGER.info(serviceJson);
//        LOGGER.info("---------------------------Service---------------------------");
//        return ResponseEntity.ok(map(serviceService.update(serviceJson), ServiceFullDto.class));
//    }

    @PostMapping("/update")
    private ResponseEntity<ServiceFullDto> update(@RequestParam String serviceJson,
                                                  @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Service---------------------------");
        LOGGER.info(serviceJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Service---------------------------");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            LOGGER.info("multipart file not null");
            return ResponseEntity.ok(map(serviceService.update(serviceJson, multipartFile), ServiceFullDto.class));
        } else {
            LOGGER.info("multipart file is null!");
            return ResponseEntity.ok(map(serviceService.update(serviceJson), ServiceFullDto.class));
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.delete(id));
    }
}
