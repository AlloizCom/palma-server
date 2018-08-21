package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.BookDto;
import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    private static final Logger LOGGER = Logger.getLogger(BookController.class);


    @GetMapping("/find-all")
    private ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.ok(bookService.findAll().stream()
                .map(book -> map(book, BookDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<BookDto>> findAllAvailable() {
        return ResponseEntity.ok(bookService.findAllAvailable().stream()
                .map(book -> map(book, BookDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<BookDto> findOneAvailale(@PathVariable Long id) {
        return ResponseEntity.ok(map(bookService.findOneAvailable(id), BookDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<BookDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(map(bookService.findOne(id), BookDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<BookDto> save(@RequestBody BookDto book) {
        LOGGER.info("---------------------------Book---------------------------");
        LOGGER.info(book);
        LOGGER.info("---------------------------Book---------------------------");
        return ResponseEntity
                .ok(map(bookService.save(map(book, Book.class)), BookDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<BookDto> update(@RequestBody BookDto book) {
        LOGGER.info("---------------------------Book---------------------------");
        LOGGER.info(book);
        LOGGER.info("---------------------------Book---------------------------");
        return ResponseEntity
                .ok(map(bookService.update(map(book, Book.class)), BookDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.delete(id));
    }

}
