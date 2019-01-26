package com.alloiz.palma.server.controller.payment;

import com.alloiz.palma.server.dto.payment.BookFullDto;
import com.alloiz.palma.server.dto.payment.BookShortDto;
import com.alloiz.palma.server.model.payment.Book;
import com.alloiz.palma.server.service.payment.PaymentBookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/payment_book")
public class PaymentBookController
{

    private static final Logger LOGGER = Logger.getLogger(PaymentBookController.class);

    @Autowired
    private PaymentBookService paymentBookService;

    @GetMapping("/find-all")
    private ResponseEntity<List<BookShortDto>> findAll() {
        return ResponseEntity.ok(paymentBookService.findAll().stream()
                .map(book -> map(book, BookFullDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<BookFullDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(map(paymentBookService.findOne(id), BookFullDto.class));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<BookShortDto>> findAllAvailable() {
        return ResponseEntity.ok(paymentBookService.findAllAvailable().stream()
                .map(book -> map(book, BookFullDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<BookFullDto> findOneAvailable(@PathVariable Long id) {
        return ResponseEntity.ok(map(paymentBookService.findOneAvailable(id), BookFullDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<BookFullDto> save(@RequestBody Book book) {
        LOGGER.info("---------------------------Book---------------------------");
        LOGGER.info(book);
        LOGGER.info("---------------------------Book---------------------------");
        return ResponseEntity.ok(map(paymentBookService.save(book), BookFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<BookFullDto> update(@RequestBody Book book) {
        LOGGER.info("---------------------------Book---------------------------");
        LOGGER.info(book);
        LOGGER.info("---------------------------Book---------------------------");

        return ResponseEntity.ok(map(paymentBookService.update(book), BookFullDto.class));

    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(paymentBookService.delete(id));
    }

    @DeleteMapping("/delete")
    private ResponseEntity<Boolean> delete(@RequestBody Book book) {
        return ResponseEntity.ok(paymentBookService.delete(book));
    }
}
