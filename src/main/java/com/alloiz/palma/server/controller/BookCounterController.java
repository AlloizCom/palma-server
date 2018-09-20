package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.model.BookCounter;
import com.alloiz.palma.server.service.BookCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/counter")
public class BookCounterController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private BookCounterService bookCounterService;

    @GetMapping("/notify")
    public BookCounter getNotification() {
        template.convertAndSend("/booking", bookCounterService.getCounter(1L));
        return bookCounterService.getCounter(1L);
    }

    @GetMapping("/reset")
    public ResponseEntity<BookCounter> resetCounter(){
        return ResponseEntity.ok(bookCounterService.resetCounter(1L));
    }

    @GetMapping("/increment")
    public ResponseEntity<BookCounter> incrementCounter(){
        return ResponseEntity.ok(bookCounterService.incrementCounter(1L));
    }
}
