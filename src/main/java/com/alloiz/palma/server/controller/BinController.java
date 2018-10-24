package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.BinDto;
import com.alloiz.palma.server.model.Bin;
import com.alloiz.palma.server.model.enums.Language;
import com.alloiz.palma.server.service.BinService;
import com.alloiz.palma.server.service.PayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/bin")
public class BinController {

    private static final Logger LOGGER = Logger.getLogger(BinController.class);

    @Autowired
    private BinService binService;

    @Autowired
    private PayService payService;

    @GetMapping("/find-all")
    private ResponseEntity<List<BinDto>> findAll() {
        return ResponseEntity.ok(binService.findAll().stream()
                .map(book -> map(book, BinDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<BinDto>> findAllAvailable() {
        return ResponseEntity.ok(binService.findAllAvailable().stream()
                .map(book -> map(book, BinDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<BinDto> findOneAvailale(@PathVariable Long id) {
        return ResponseEntity.ok(map(binService.findOneAvailable(id), BinDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<BinDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(map(binService.findOne(id), BinDto.class));
    }

    @PostMapping("/save/{language}")
    private ResponseEntity<BinDto> save(@RequestBody BinDto bin,
                                         @PathVariable Language language) {
        LOGGER.info("---------------------------Bin---------------------------");
        LOGGER.info(bin);
        LOGGER.info(language);
        LOGGER.info("---------------------------Bin---------------------------");
        return ResponseEntity
                .ok(map(binService.save(map(bin, Bin.class),language), BinDto.class));
    }

    @PostMapping("/pay/{language}")
    private ResponseEntity<String> pay(@RequestBody BinDto book,
                                       @PathVariable Language language) {
        binService.save(map(book, Bin.class),language);
        return ResponseEntity.ok(payService.getButton(map(book, Bin.class)));
    }

    @PostMapping("/update")
    private ResponseEntity<BinDto> update(@RequestBody BinDto book) {
        LOGGER.info("---------------------------Bin---------------------------");
        LOGGER.info(book);
        LOGGER.info("---------------------------Bin---------------------------");
        return ResponseEntity
                .ok(map(binService.update(map(book, Bin.class)), BinDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(binService.delete(id));
    }

}
