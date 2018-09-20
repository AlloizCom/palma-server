package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.model.CallbackCounter;
import com.alloiz.palma.server.service.CallbackCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/callback-gcounter")
public class CallbackCounterController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private CallbackCounterService callbackCounterService;

    @GetMapping("/notify-callback")
    public CallbackCounter getNotification() {
        template.convertAndSend("/callback", callbackCounterService.getCounter(1L));
        return callbackCounterService.getCounter(1L);
    }

    @GetMapping("/reset-callback")
    public ResponseEntity<CallbackCounter> resetCounter(){
        return ResponseEntity.ok(callbackCounterService.resetCounter(1L));
    }

    @GetMapping("/increment-callback")
    public ResponseEntity<CallbackCounter> incrementCounter(){
        return ResponseEntity.ok(callbackCounterService.incrementCounter(1L));
    }
}
