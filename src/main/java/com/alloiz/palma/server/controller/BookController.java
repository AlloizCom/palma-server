package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.BookByPage;
import com.alloiz.palma.server.dto.BookDto;
import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.enums.Language;
import com.alloiz.palma.server.model.enums.OrderStatus;
import com.alloiz.palma.server.service.BookService;
import com.alloiz.palma.server.service.PayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/book")
public class BookController {

    private static final Logger LOGGER = Logger.getLogger(BookController.class);
    @Autowired
    private BookService bookService;
    @Autowired
    private PayService payService;

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

    @GetMapping("/change-order-status/{id}/{orderStatus}")
    private ResponseEntity<BookDto> changeStatus(@PathVariable Long id,
                                                 @PathVariable OrderStatus orderStatus) {
        return ResponseEntity.ok(map(bookService.changeStatus(id,orderStatus), BookDto.class));
    }

    @GetMapping("/find-all-book-by-page/{page}/{count}")
    private ResponseEntity<List<BookDto>> findAllPageable(@PathVariable Integer page,
                                                          @PathVariable Integer count) {
        return ResponseEntity.ok(bookService
                .findAll(new PageRequest(page, count))
                .stream().map(schedule -> map(schedule, BookDto.class))
                .collect(toList()));
    }

    @GetMapping("/find-all-book-by-page-available/{page}/{count}")
    private ResponseEntity<BookByPage> findAllPageableAvailable(@PathVariable Integer page,
                                                                @PathVariable Integer count) {
        return ResponseEntity.ok(bookService
                .findAllByAvailable(new PageRequest(page, count)));
    }

    @PostMapping("/save/{language}")
    private ResponseEntity<BookDto> save(@RequestBody BookDto book,
                                         @PathVariable Language language) {
        LOGGER.info("---------------------------Book---------------------------");
        LOGGER.info(book);
        LOGGER.info(language);
        LOGGER.info("---------------------------Book---------------------------");
        return ResponseEntity
                .ok(map(bookService.save(map(book, Book.class),language,false), BookDto.class));
    }

    @PostMapping("/pay/{language}")
    private ResponseEntity<String> pay(@RequestBody BookDto book,
                                       @PathVariable Language language) {
        bookService.save(map(book, Book.class),language,true);
        return ResponseEntity.ok(payService.getButton(map(book, Book.class)));
    }

    @PostMapping("/update")
    private ResponseEntity<BookDto> update(@RequestBody BookDto book) {
        LOGGER.info("---------------------------Book---------------------------");
        LOGGER.info(book);
        LOGGER.info("---------------------------Book---------------------------");
        return ResponseEntity
                .ok(map(bookService.update(map(book, Book.class)), BookDto.class));
    }

    @PostMapping("/cancel")
    private ResponseEntity<BookDto> cancelBook(@RequestBody BookDto book) {
        LOGGER.info("--------------------Cancel Book---------------------------");
        LOGGER.info(book);
        LOGGER.info("--------------------Cancel Book---------------------------");
        return ResponseEntity
                .ok(map(bookService.cancelBook(map(book, Book.class)), BookDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.delete(id));
    }

}
