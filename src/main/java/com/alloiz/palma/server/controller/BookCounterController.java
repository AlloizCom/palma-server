package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.model.BookCounter;
import com.alloiz.palma.server.service.BookCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class BookCounterController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private BookCounterService bookCounterService;

    @GetMapping("/notify")
    public BookCounter getNotification(){
        template.convertAndSend("/booking",bookCounterService.getCounter(1L));
        return bookCounterService.getCounter(1L);
    }
}
