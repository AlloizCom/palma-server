package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.MainPageFullDto;
import com.alloiz.palma.server.dto.MainPageShortDto;
import com.alloiz.palma.server.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/main-page")
public class MainPageController {

    @Autowired
    private MainPageService mainPageService;

    @GetMapping("/find-all")
    private ResponseEntity<List<MainPageShortDto>> findAll() {
        return new ResponseEntity<>(mainPageService.findAll().stream()
                .map(mainPage -> map(mainPage, MainPageFullDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<MainPageShortDto>> findAllAvailable() {
        return new ResponseEntity<>(mainPageService.findAllAvailable().stream()
                .map(room -> map(room, MainPageFullDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<MainPageFullDto> findOneAvailale(@PathVariable Long id) {
        return new ResponseEntity<>(map(mainPageService.findOneAvailable(id), MainPageFullDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<MainPageFullDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(mainPageService.findOne(id), MainPageFullDto.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<MainPageFullDto> save(@RequestParam String mainPageJson,
                                                 @RequestParam(required = false) MultipartFile[] multipartFiles) {
        return ResponseEntity.ok(map(mainPageService.save(mainPageJson, multipartFiles), MainPageFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<MainPageFullDto> update(@RequestParam String mainPageJson,
                                                   @RequestParam(required = false) MultipartFile[] multipartFiles) {
        if (multipartFiles != null && multipartFiles.length != 0) {
            return ResponseEntity.ok(map(mainPageService.update(mainPageJson, multipartFiles), MainPageFullDto.class));
        } else {
            return ResponseEntity.ok(map(mainPageService.update(mainPageJson), MainPageFullDto.class));
        }
    }

    @PostMapping("/add-images")
    private ResponseEntity<MainPageFullDto> addImages(@RequestParam MultipartFile[] multipartFiles,
                                                      @RequestParam Long mainPageId) {
        return ResponseEntity.ok(map(mainPageService.addImages(mainPageId, multipartFiles), MainPageFullDto.class));
    }

    @DeleteMapping("/delete-image/{mainPageId}/image/{imageId}")
    private ResponseEntity<Boolean> deleteImage(
            @PathVariable Long mainPageId,
            @PathVariable Long imageId) {
        return ResponseEntity.ok(mainPageService.deleteImage(mainPageId, imageId));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(mainPageService.delete(id));
    }
}
