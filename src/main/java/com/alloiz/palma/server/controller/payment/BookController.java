package com.alloiz.palma.server.controller.payment;

import com.alloiz.palma.server.dto.payment.BookFullDto;
import com.alloiz.palma.server.dto.payment.BookShortDto;
import com.alloiz.palma.server.model.payment.Book;
import com.alloiz.palma.server.service.payment.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/payment/book")
public class BookController {

    private static final Logger LOGGER = Logger.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/find-all")
    private ResponseEntity<List<BookShortDto>> findAll() {
        return ResponseEntity.ok(bookService.findAll().stream()
                .map(book -> map(book, BookFullDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<BookFullDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(map(bookService.findOne(id), BookFullDto.class));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<BookShortDto>> findAllAvailable() {
        return ResponseEntity.ok(bookService.findAllAvailable().stream()
                .map(book -> map(book, BookFullDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<BookFullDto> findOneAvailable(@PathVariable Long id) {
        return ResponseEntity.ok(map(bookService.findOneAvailable(id), BookFullDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<BookFullDto> save(@RequestBody Book book) {
        LOGGER.info("---------------------------Book---------------------------");
        LOGGER.info(book);
        LOGGER.info("---------------------------Book---------------------------");
        return ResponseEntity.ok(map(bookService.save(book), BookFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<BookFullDto> update(@RequestBody Book book) {
        LOGGER.info("---------------------------Book---------------------------");
        LOGGER.info(book);
        LOGGER.info("---------------------------Book---------------------------");

        return ResponseEntity.ok(map(bookService.update(book), BookFullDto.class));

    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.delete(id));
    }

    @DeleteMapping("/delete")
    private ResponseEntity<Boolean> delete(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.delete(book));
    }
}
