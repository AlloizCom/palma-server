package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.RoomForSaleFullDto;
import com.alloiz.palma.server.service.RoomForSaleService;
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
@RequestMapping("/room-for-sale")
public class RoomForSaleController {

    private static final Logger LOGGER = Logger.getLogger(RoomForSaleController.class);

    @Autowired
    private RoomForSaleService roomForSaleService;

    @GetMapping("/find-all")
    private ResponseEntity<List<RoomForSaleFullDto>> findAll() {
        return new ResponseEntity<>(roomForSaleService.findAll()
                .stream()
                .map(room -> map(room, RoomForSaleFullDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<RoomForSaleFullDto>> findAllAvailable() {
        return new ResponseEntity<>(roomForSaleService.findAllAvailable()
                .stream()
                .map(room -> map(room, RoomForSaleFullDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<RoomForSaleFullDto> findOneAvailale(@PathVariable Long id) {
        return new ResponseEntity<>(map(roomForSaleService
                .findOneAvailable(id), RoomForSaleFullDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<RoomForSaleFullDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(roomForSaleService.findOne(id), RoomForSaleFullDto.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<RoomForSaleFullDto> save(@RequestParam String roomForSaleJson,
                                             @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------RoomForSale---------------------------");
        LOGGER.info(roomForSaleJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------RoomForSale---------------------------");
        return ResponseEntity.ok(map(roomForSaleService.save(roomForSaleJson, multipartFile), RoomForSaleFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<RoomForSaleFullDto> update(@RequestParam String roomForSaleJson, @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------RoomForSale---------------------------");
        LOGGER.info(roomForSaleJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------RoomForSale---------------------------");
        return ResponseEntity.ok(map(roomForSaleService.update(roomForSaleJson, multipartFile), RoomForSaleFullDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        LOGGER.info("--------------------------DELETE--------------------------");
        LOGGER.info("RoomForSale ID:" + id);
        return ResponseEntity.ok(roomForSaleService.delete(id));
    }

}
