package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.ScheduleByPages;
import com.alloiz.palma.server.dto.ScheduleDto;
import com.alloiz.palma.server.model.Schedule;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.repository.utils.ChangeRoomForSale;
import com.alloiz.palma.server.service.ScheduleService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

/**
 * Controller for Schedule
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private static final Logger LOGGER = Logger.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/find-all")
    private ResponseEntity<List<ScheduleDto>> findAll() {
        return ResponseEntity.ok(scheduleService.findAll().stream()
                .map(schedule -> map(schedule, ScheduleDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<ScheduleDto>> findAllAvailable() {
        return ResponseEntity.ok(scheduleService.findAllAvailable().stream()
                .map(schedule -> map(schedule, ScheduleDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/find-all-archived")
    private ResponseEntity<List<ScheduleDto>> findAllArchived() {
        return ResponseEntity.ok(scheduleService.findAllArchived().stream()
                .map(schedule -> map(schedule, ScheduleDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<ScheduleDto> findOne(@PathVariable Long id) {
        return ResponseEntity
                .ok(map(scheduleService.findOne(id), ScheduleDto.class));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<ScheduleDto> findOneAvailale(@PathVariable Long id) {
        return ResponseEntity
                .ok(map(scheduleService.findOneAvailable(id), ScheduleDto.class));
    }

    @GetMapping("/find-all-schedule-by-page/{page}/{count}")
    private ResponseEntity<List<ScheduleDto>> findAllPageable(@PathVariable Integer page,
                                                              @PathVariable Integer count) {
        return ResponseEntity.ok(scheduleService
                .findAll(new PageRequest(page, count))
                .stream().map(schedule -> map(schedule, ScheduleDto.class))
                .collect(toList()));
    }

    @GetMapping("/find-all-schedule-by-page-available/{page}/{count}")
    private ResponseEntity<ScheduleByPages> findAllPageableAvailable(@PathVariable Integer page,
                                                                     @PathVariable Integer count) {
        return ResponseEntity.ok(scheduleService
                .findAllByAvailable(new PageRequest(page, count)));
    }

    @GetMapping("/find-all-schedule-by-date-type")
    private ResponseEntity<List<ScheduleDto>> findAllPageableAvailable(@JsonFormat(pattern = "dd-MM-yyyy") @RequestParam Timestamp date, @RequestParam RoomType type) {
        return ResponseEntity.ok(scheduleService
                .findAllByAvailableAndTodayAfterAndRoomType(date, type).stream().map(schedule -> map(schedule, ScheduleDto.class)).collect(toList()));
    }

    @PostMapping("/save")
    private ResponseEntity<ScheduleDto> save(@RequestBody ScheduleDto schedule) {
        LOGGER.info(">>> Schedule Controller");
        LOGGER.info("--------------------Schedule Save--------------------");
        LOGGER.info(schedule);
        LOGGER.info("--------------------Schedule Save--------------------");
        return ResponseEntity
                .ok(map(scheduleService.save(map(schedule, Schedule.class)), ScheduleDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<ScheduleDto> update(@RequestBody ScheduleDto schedule) {
        LOGGER.info(">>> Schedule Controller");
        LOGGER.info("--------------------Schedule Update--------------------");
        LOGGER.info(schedule);
        LOGGER.info("--------------------Schedule Update--------------------");
        return ResponseEntity
                .ok(map(scheduleService.update(map(schedule, Schedule.class)), ScheduleDto.class));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        LOGGER.info(">>> Schedule Controller");
        LOGGER.info("--------------------Schedule Delete--------------------");
        LOGGER.info("Schedule id: " + id);
        LOGGER.info("--------------------Schedule Delete--------------------");
        return ResponseEntity.ok(scheduleService.delete(id));
    }
}
