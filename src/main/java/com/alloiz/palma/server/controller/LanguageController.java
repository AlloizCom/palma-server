package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.model.enums.Language;
import com.alloiz.palma.server.service.LanguageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/language")
public class LanguageController {

    private static final Logger LOGGER = Logger.getLogger(LanguageController.class);

    @Autowired
    private LanguageService languageService;

    @GetMapping("/get-translate/{language}")
    private ResponseEntity<String> getTranslate(@PathVariable String language) {
        LOGGER.info("---------------------------Language---------------------------");
        LOGGER.info(language);
        LOGGER.info("---------------------------Language---------------------------");
        return new ResponseEntity(languageService.getTranslateFile(language), HttpStatus.OK);
    }


    @GetMapping("/get-all-translates")
    private ResponseEntity<List<String>> getAllTranslates() {
        LOGGER.info("---------------------------Language---------------------------");
        return new ResponseEntity<>(languageService.getAllTranslate(), HttpStatus.OK);
    }

}
