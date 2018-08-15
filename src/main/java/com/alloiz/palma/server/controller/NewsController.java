package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.NewsFullDto;
import com.alloiz.palma.server.dto.NewsShortDto;
import com.alloiz.palma.server.dto.RoomShortDto;
import com.alloiz.palma.server.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/find-all")
    private ResponseEntity<List<NewsShortDto>> findAll() {
        return new ResponseEntity<>(newsService.findAll().stream()
                .map(worker -> map(worker, NewsShortDto.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<NewsShortDto>> findAllAvailable() {
        return new ResponseEntity<>(newsService.findAllAvailable().stream()
                .map(worker -> map(worker, NewsShortDto.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<RoomShortDto> findOneAvailale(@PathVariable Long id) {
        return new ResponseEntity<>(map(newsService.findOneAvailable(id), RoomShortDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<RoomShortDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(newsService.findOne(id), RoomShortDto.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<NewsFullDto> save(@RequestParam String newsJson,
                                             @RequestParam(required = false) MultipartFile multipartFile) {
        return ResponseEntity.ok(map(newsService.save(newsJson, multipartFile), NewsFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<NewsFullDto> update(@RequestParam String newsJson, @RequestParam(required = false) MultipartFile multipartFile) {
        //if (multipartFiles != null && !multipartFile.isEmpty()) {
        //  return ResponseEntity.ok(map(roomService.update(roomJson, multipartFile), WorkerFullDto.class));
        //} else {
        return ResponseEntity.ok(map(newsService.update(newsJson, multipartFile), NewsFullDto.class));
        //}
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.delete(id));
    }
}
