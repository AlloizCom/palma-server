package com.alloiz.palma.server.controller.payment;

import com.alloiz.palma.server.dto.payment.DescriptionFullDto;
import com.alloiz.palma.server.dto.payment.DescriptionShortDto;
import com.alloiz.palma.server.model.payment.Description;
import com.alloiz.palma.server.service.payment.DescriptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/payment/description")
public class DescriptionController {

    private static final Logger LOGGER = Logger.getLogger(DescriptionController.class);

    @Autowired
    private DescriptionService descriptionService;

    @GetMapping("/find-all")
    private ResponseEntity<List<DescriptionShortDto>> findAll() {
        return ResponseEntity.ok(descriptionService.findAll().stream()
                .map(description -> map(description, DescriptionFullDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<DescriptionFullDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(map(descriptionService.findOne(id), DescriptionFullDto.class));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<DescriptionShortDto>> findAllAvailable() {
        return ResponseEntity.ok(descriptionService.findAllAvailable().stream()
                .map(description -> map(description, DescriptionFullDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<DescriptionFullDto> findOneAvailable(@PathVariable Long id) {
        return ResponseEntity.ok(map(descriptionService.findOneAvailable(id), DescriptionFullDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<DescriptionFullDto> save(@RequestBody Description description) {
        LOGGER.info("---------------------------Description---------------------------");
        LOGGER.info(description);
        LOGGER.info("---------------------------Description---------------------------");
        return ResponseEntity.ok(map(descriptionService.save(description), DescriptionFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<DescriptionFullDto> update(@RequestBody Description description) {
        LOGGER.info("---------------------------Description---------------------------");
        LOGGER.info(description);
        LOGGER.info("---------------------------Description---------------------------");

        return ResponseEntity.ok(map(descriptionService.update(description), DescriptionFullDto.class));

    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(descriptionService.delete(id));
    }

    @DeleteMapping("/delete")
    private ResponseEntity<Boolean> delete(@RequestBody Description description) {
        return ResponseEntity.ok(descriptionService.delete(description));
    }
}
