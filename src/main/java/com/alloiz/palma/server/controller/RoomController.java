package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.RoomFullDto;
import com.alloiz.palma.server.dto.RoomShortDto;
import com.alloiz.palma.server.model.Room;
import com.alloiz.palma.server.model.UserEntity;
import com.alloiz.palma.server.service.RoomService;
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

    @Autowired
    private RoomService roomService;

    @GetMapping("/find-all")
    private ResponseEntity<List<RoomShortDto>> findAll() {
        return new ResponseEntity<>(roomService.findAll().stream()
                .map(room -> map(room, RoomFullDto.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<RoomShortDto>> findAllAvailable() {
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
        return ResponseEntity.ok(map(roomService.save(roomJson, multipartFiles), RoomFullDto.class));
//        return ResponseEntity.ok(map(workerService.save(workerJson, multipartFile), WorkerFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<RoomFullDto> update(@RequestParam String roomJson, @RequestParam(required = false) MultipartFile[] multipartFiles) {
        //if (multipartFiles != null && !multipartFile.isEmpty()) {
          //  return ResponseEntity.ok(map(roomService.update(roomJson, multipartFile), WorkerFullDto.class));
        //} else {
            return ResponseEntity.ok(map(roomService.update(roomJson), RoomFullDto.class));
        //}
    }

    @PostMapping("/add-images")
    private ResponseEntity<RoomFullDto> addImages(@RequestParam MultipartFile[] multipartFiles, @RequestParam Long roomId) {
        return ResponseEntity.ok(map(roomService.addImages(roomId, multipartFiles), RoomFullDto.class));
    }

    @PostMapping("/delete-image")
    private ResponseEntity<Boolean> deleteImage(@RequestParam Long roomId,@RequestParam Long imageId) {
        return ResponseEntity.ok(roomService.deleteImage(roomId, imageId));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.delete(id));
    }
}
