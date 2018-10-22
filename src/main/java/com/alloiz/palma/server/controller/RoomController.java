package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.RoomFullDto;
import com.alloiz.palma.server.service.RoomService;
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
@RequestMapping("/room")
public class RoomController {

    private static final Logger LOGGER = Logger.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @GetMapping("/find-all")
    private ResponseEntity<List<RoomFullDto>> findAll() {
        return new ResponseEntity<>(roomService.findAll().stream()
                .map(room -> map(room, RoomFullDto.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<RoomFullDto>> findAllAvailable() {
        return new ResponseEntity<>(roomService.findAllAvailable().stream()
                .map(room -> map(room, RoomFullDto.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<RoomFullDto> findOneAvailale(@PathVariable Long id) {
        return new ResponseEntity<>(map(roomService.findOneAvailable(id), RoomFullDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<RoomFullDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(roomService.findOne(id), RoomFullDto.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<RoomFullDto> save(@RequestParam String roomJson,
                                             @RequestParam(required = false) MultipartFile[] multipartFiles) {
        LOGGER.info("---------------------------Room---------------------------");
        LOGGER.info(roomJson);
        LOGGER.info(multipartFiles.length);
        LOGGER.info("---------------------------Room---------------------------");
        return ResponseEntity.ok(map(roomService.save(roomJson, multipartFiles), RoomFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<RoomFullDto> update(@RequestParam String roomJson, @RequestParam(required = false) MultipartFile[] multipartFiles) {
        LOGGER.info("---------------------------Room---------------------------");
        LOGGER.info(roomJson);
        LOGGER.info("Img: " + multipartFiles.length);
        LOGGER.info("---------------------------Room---------------------------");
        if (multipartFiles != null && multipartFiles.length != 0&&!multipartFiles[0].isEmpty()) {
            return ResponseEntity.ok(map(roomService.update(roomJson, multipartFiles), RoomFullDto.class));
        } else {
            return ResponseEntity.ok(map(roomService.update(roomJson), RoomFullDto.class));
        }
    }

    @PostMapping("/add-images")
    private ResponseEntity<RoomFullDto> addImages(@RequestParam MultipartFile[] multipartFiles, @RequestParam Long roomId) {
        return ResponseEntity.ok(map(roomService.addImages(roomId, multipartFiles), RoomFullDto.class));
    }

    @PostMapping("/add-images/{roomId}")
    private ResponseEntity<RoomFullDto> addImagesPV(@RequestParam MultipartFile[] multipartFiles, @PathVariable Long roomId) {
        return ResponseEntity.ok(map(roomService.addImages(roomId, multipartFiles), RoomFullDto.class));
    }

    @DeleteMapping("/delete-image/{roomId}/image/{imageId}")
    private ResponseEntity<Boolean> deleteImage(
            @PathVariable Long roomId,
            @PathVariable Long imageId) {
        return ResponseEntity.ok(roomService.deleteImage(roomId, imageId));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.delete(id));
    }

//    @GetMapping("/find-one-with-price/{id}")
//    private ResponseEntity<RoomWithTariff> findOneWithPrice(@PathVariable Long id) {
//        Room room = roomService.findOne(id);
//        Tariff tariffs = tariffService.findByRoomTypeAndDateNow(room.getType());
//        return new ResponseEntity<>(map(room, RoomWithTariff.class)
//                .setPrice(tariffs.getPrice()), HttpStatus.OK);
//    }
    //    @GetMapping("/find-all-available-kids-adult/{kidsPlaces}/{adultPlaces}")
//    private ResponseEntity<List<RoomFullDto>> findAllByAdultPlacesAndKidsPlacesAndAvailable(
//            @PathVariable Integer kidsPlaces,
//            @PathVariable Integer adultPlaces
//    ) {
//        return new ResponseEntity<>(roomService.findAllByAdultPlacesAndKidsPlacesAndAvailable(
//                true,
//                kidsPlaces,
//                adultPlaces
//        ).stream()
//                .map(room -> map(room, RoomFullDto.class))
//                .collect(Collectors.toList()), HttpStatus.OK);
//    }
//    @PostMapping("/find-by-book-params")
//    private ResponseEntity<List<RoomWithTariff>> findRyRoomsParams(@RequestBody RoomParams roomParams){
//        LOGGER.info("-----------------FIND ROOM BY PARAMS---------------------");
//        LOGGER.info(roomParams);
//        return new ResponseEntity<>(roomService.findRoomsByRoomParams(roomParams)
//                .stream()
//                    .map(room -> map(room, RoomWithTariff.class)
//                        .setPrice(tariffService.findByRoomTypeAndDateNow(room.getType()).getPrice())
//                    .setImages(room.getImages().subList(0,1)))
//                .collect(Collectors.toList()), HttpStatus.OK);
//
//    }
//
//    @PostMapping("/find-by-book-params-with-room-type")
//    private ResponseEntity<List<RoomWithTariff>> findRyRoomsParamsWithRoomType(@RequestBody RoomParams roomParams){
//        LOGGER.info("-----------------FIND ROOM BY PARAMS WITH ROOM TYPE---------------------");
//        LOGGER.info(roomParams);
//        return new ResponseEntity<>(roomService.findRoomsByRoomParamsWithRoomType(roomParams)
//                .stream()
//                .map(room -> map(room, RoomWithTariff.class)
//                        .setPrice(tariffService.findByRoomTypeAndDateNow(room.getType()).getPrice()))
//                .collect(Collectors.toList()), HttpStatus.OK);
//
//    }
//
//    @GetMapping("/find-all-only-with-images")
//    private ResponseEntity<List<RoomMiddleDto>> findAllOnlyWithImages() {
//        return new ResponseEntity<>(roomService
//                .findAll()
//                .stream()
//                .map(room -> map(room, RoomMiddleDto.class))
//                .collect(Collectors.toList()), HttpStatus.OK);
//    }
//
//    @GetMapping("/find-all-available-only-with-images")
//    private ResponseEntity<List<RoomMiddleDto>> findAllAvailableOnlyWithImages() {
//        return new ResponseEntity<>(roomService
//                .findAllAvailable()
//                .stream()
//                .map(room -> map(room, RoomMiddleDto.class))
//                .collect(Collectors.toList()), HttpStatus.OK);
//    }
}