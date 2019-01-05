package com.alloiz.palma.server.controller.payment;

import com.alloiz.palma.server.dto.MainPageFullDto;
import com.alloiz.palma.server.dto.payment.BookFullDto;
import com.alloiz.palma.server.dto.payment.BookShortDto;
import com.alloiz.palma.server.dto.payment.MultiLanguageNameFullDto;
import com.alloiz.palma.server.model.payment.Book;
import com.alloiz.palma.server.model.payment.MultiLanguageName;
import com.alloiz.palma.server.service.payment.MultiLanguageNameService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

@RestController("/payment/MultiLanguageName")
public class MultiLanguageNameController {

    private static final Logger LOGGER = Logger.getLogger(BookController.class);


    @Autowired
    private MultiLanguageNameService multiLanguageNameService;

    @GetMapping("/find-all")
    private ResponseEntity<List<MultiLanguageNameFullDto>> findAll() {
        return ResponseEntity.ok(multiLanguageNameService.findAll().stream()
                .map(mln -> map(mln, MultiLanguageNameFullDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<MultiLanguageNameFullDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(map(multiLanguageNameService.findOne(id), MultiLanguageNameFullDto.class));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<MultiLanguageNameFullDto>> findAllAvailable() {
        return ResponseEntity.ok(multiLanguageNameService.findAllAvailable().stream()
                .map(mln -> map(mln, MultiLanguageNameFullDto.class)).collect(Collectors.toList()));
    }
    @PostMapping("/save")
    private ResponseEntity<MultiLanguageNameFullDto> save(@RequestBody MultiLanguageName multiLanguageName) {
        LOGGER.info("---------------------------MLN---------------------------");
        LOGGER.info(multiLanguageName);
        LOGGER.info("---------------------------MLN---------------------------");
        return ResponseEntity.ok(map(multiLanguageNameService.save(multiLanguageName), MultiLanguageNameFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<MultiLanguageNameFullDto> update(@RequestBody MultiLanguageName multiLanguageName) {
        LOGGER.info("---------------------------MLN---------------------------");
        LOGGER.info(multiLanguageName);
        LOGGER.info("---------------------------MLN---------------------------");

        return ResponseEntity.ok(map(multiLanguageNameService.update(multiLanguageName), MultiLanguageNameFullDto.class));

    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(multiLanguageNameService.delete(id));
    }

}
