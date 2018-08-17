package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.TariffDto;
import com.alloiz.palma.server.model.Tariff;
import com.alloiz.palma.server.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/tariff")
public class TariffController {

    @Autowired
    private TariffService tariffService;

    @GetMapping("/find-all")
    private ResponseEntity<List<TariffDto>> findAll() {
        return ResponseEntity.ok(tariffService.findAll().stream()
                .map(tariff -> map(tariff, TariffDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<TariffDto>> findAllAvailable() {
        return ResponseEntity.ok(tariffService.findAllAvailable().stream()
                .map(tariff -> map(tariff, TariffDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<TariffDto> findOneAvailale(@PathVariable Long id) {
        return ResponseEntity.ok(map(tariffService.findOneAvailable(id), TariffDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<TariffDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(map(tariffService.findOne(id), TariffDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<TariffDto> save(@RequestBody TariffDto tariff) {
        return ResponseEntity
                .ok(map(tariffService.save(map(tariff, Tariff.class)), TariffDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<TariffDto> update(@RequestBody TariffDto tariff) {
        return ResponseEntity
                .ok(map(tariffService.update(map(tariff, Tariff.class)), TariffDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(tariffService.delete(id));
    }
}
