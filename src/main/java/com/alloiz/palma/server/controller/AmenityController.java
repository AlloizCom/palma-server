package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.AmenityFullDto;
import com.alloiz.palma.server.dto.AmenityShortDto;
import com.alloiz.palma.server.service.AmenityService;
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
@RequestMapping("/amenity")
public class AmenityController {

    private static final Logger LOGGER = Logger.getLogger(AmenityController.class);

    @Autowired
    private AmenityService amenityService;

    @GetMapping("/find-all")
    private ResponseEntity<List<AmenityShortDto>> findAll() {
        return new ResponseEntity<>(amenityService.findAll().stream()
                .map(amenity -> map(amenity, AmenityFullDto.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<AmenityShortDto>> findAllAvailable() {
        return new ResponseEntity<>(amenityService.findAllAvailable().stream()
                .map(amenity -> map(amenity, AmenityFullDto.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<AmenityFullDto> findOneAvailale(@PathVariable Long id) {
        return new ResponseEntity<>(map(amenityService.findOneAvailable(id), AmenityFullDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<AmenityFullDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(amenityService.findOne(id), AmenityFullDto.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<AmenityFullDto> save(@RequestParam String amenityJson,
                                                @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Amenity---------------------------");
        LOGGER.info(amenityJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Amenity---------------------------");
        return ResponseEntity.ok(map(amenityService.save(amenityJson, multipartFile), AmenityFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<AmenityFullDto> update(@RequestParam String amenityJson, @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Amenity---------------------------");
        LOGGER.info(amenityJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Amenity---------------------------");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            LOGGER.info("multipart file not null");
            return ResponseEntity.ok(map(amenityService.update(amenityJson, multipartFile), AmenityFullDto.class));
        } else {
            LOGGER.info("multipart file is null!");
            return ResponseEntity.ok(map(amenityService.update(amenityJson), AmenityFullDto.class));
        }
    }

    @PostMapping("/update-image/{id}")
    private ResponseEntity<AmenityFullDto> updateImage(@RequestParam MultipartFile multipartFile, @PathVariable Long id) {
        return ResponseEntity.ok(map(amenityService.updateImage(multipartFile, id), AmenityFullDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(amenityService.delete(id));
    }

}
