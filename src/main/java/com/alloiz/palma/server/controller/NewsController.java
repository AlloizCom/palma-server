package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.NewsByPages;
import com.alloiz.palma.server.dto.NewsFullDto;
import com.alloiz.palma.server.dto.NewsShortDto;
import com.alloiz.palma.server.dto.RoomShortDto;
import com.alloiz.palma.server.service.NewsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/news")
public class NewsController {

        private static final Logger LOGGER = Logger.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    @GetMapping("/find-all-news-by-page/{page}/{count}")
    private ResponseEntity<List<NewsFullDto>> findAllPageable(@PathVariable Integer page, @PathVariable Integer count) {
        return ResponseEntity.ok(newsService
                .findAll(new PageRequest(page, count))
                .stream().map(news -> map(news, NewsFullDto.class))
                .collect(toList()));
    }

    @GetMapping("/find-all-news-by-page-available/{page}/{count}")
    private ResponseEntity<NewsByPages> findAllPageableAvailable(@PathVariable Integer page, @PathVariable Integer count) {
        return ResponseEntity.ok(newsService.findAllByAvailable(new PageRequest(page, count)));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<NewsFullDto>> findAll() {
        return new ResponseEntity<>(newsService.findAll().stream()
                .map(news -> map(news, NewsFullDto.class)).collect(toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<NewsFullDto>> findAllAvailable() {
        return new ResponseEntity<>(newsService.findAllAvailable().stream()
                .map(news -> map(news, NewsFullDto.class)).collect(toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<NewsFullDto> findOneAvailale(@PathVariable Long id) {
        return new ResponseEntity<>(map(newsService.findOneAvailable(id), NewsFullDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<NewsFullDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(newsService.findOne(id), NewsFullDto.class), HttpStatus.OK);
    }

    @GetMapping("/get-random-array/{length}")
    private ResponseEntity<List<Integer>> getRandomArray(@PathVariable Integer length){
        return new ResponseEntity<>(newsService.generateRandomArray(length),HttpStatus.OK);
    }

    @GetMapping("/get-list-of-random-news/{amount}")
    private ResponseEntity<List<NewsFullDto>> getRandomNews(@PathVariable Integer amount) {
        return new ResponseEntity<>(newsService.findRandomNews(amount).stream()
                .map(news -> map(news, NewsFullDto.class)).collect(toList()), HttpStatus.OK);
    }

//    @GetMapping("/get-list-of-random-news/{length}")
//    private ResponseEntity<List<NewsFullDto>> getRandomArray(@PathVariable Integer length){
//        return new ResponseEntity<>(newsService.findRandomNews(length),HttpStatus.OK);
//    }

    @PostMapping("/save")
    private ResponseEntity<NewsFullDto> save(@RequestParam String newsJson,
                                             @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------News---------------------------");
        LOGGER.info(newsJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------News---------------------------");
        return ResponseEntity.ok(map(newsService.save(newsJson, multipartFile), NewsFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<NewsFullDto> update(@RequestParam String newsJson, @RequestParam(required = false) MultipartFile multipartFile) {
        //if (multipartFiles != null && !multipartFile.isEmpty()) {
        //  return ResponseEntity.ok(map(roomService.update(roomJson, multipartFile), WorkerFullDto.class));
        //} else {
        LOGGER.info("---------------------------News---------------------------");
        LOGGER.info(newsJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------News---------------------------");
        return ResponseEntity.ok(map(newsService.update(newsJson, multipartFile), NewsFullDto.class));
        //}
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        LOGGER.info("---------------------------News---------------------------");
        LOGGER.info("--------------------------DELETE--------------------------");
        LOGGER.info("------------------------CONTROLLER------------------------");
        LOGGER.info("News ID:" + id);
        LOGGER.info("---------------------------News---------------------------");
        return ResponseEntity.ok(newsService.delete(id));
    }
}
