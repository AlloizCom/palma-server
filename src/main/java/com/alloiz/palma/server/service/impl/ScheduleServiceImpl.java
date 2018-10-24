package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.dto.ScheduleByPages;
import com.alloiz.palma.server.dto.ScheduleDto;
import com.alloiz.palma.server.model.Schedule;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.repository.ScheduleRepository;
import com.alloiz.palma.server.repository.utils.ChangeRoomForSale;
import com.alloiz.palma.server.repository.utils.RoomTypeWithNumber;
import com.alloiz.palma.server.service.ScheduleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;
import static com.alloiz.palma.server.service.utils.Validation.*;
import static java.util.stream.Collectors.toList;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger LOGGER = Logger.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public Schedule findOneAvailable(Long id) {
        checkId(id);
        return scheduleRepository.findByAvailableAndId(true,id);
    }

    @Override
    public List<Schedule> findAllAvailable() {
        return scheduleRepository.findAllByAvailable(true);
    }

    @Override
    public List<Schedule> findAllArchived() {
        return scheduleRepository.findAllByAvailable(false);
    }

    @Override
    public Schedule findOne(Long id) {
        checkId(id);
        return scheduleRepository.findOne(id);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule save(Schedule schedule) {
        checkSave(schedule);
        return scheduleRepository.save(schedule.setAvailable(true)
                .setToday(Timestamp.valueOf(LocalDateTime.now())));
    }

    @Override
    public Schedule saveDefault(Schedule schedule) {
        return scheduleRepository.save(schedule.setAvailable(true));
    }

    @Override
    public Schedule update(Schedule schedule) {
        checkObjectExistsById(schedule.getId(),scheduleRepository);
        return scheduleRepository.save(findOne(schedule.getId())
                    .setFree(schedule.getFree())
                    .setToday(schedule.getToday())
                    .setRoom(schedule.getRoom())
                    .setPrice(schedule.getPrice())
                    .setAvailable(schedule.getAvailable())
        );
    }

    @Override
    public Schedule updateAfterBooking(Schedule schedule) {
        checkObjectExistsById(schedule.getId(),scheduleRepository);
        return scheduleRepository.save(findOne(schedule.getId())
                .setFree(schedule.getFree())
                .setToday(schedule.getToday())
                .setRoom(schedule.getRoom())
                .setPrice(schedule.getPrice())
                .setAvailable(schedule.getAvailable())
        );
    }

    @Override
    public List<Schedule> changePriceBetweenTwoDates(Timestamp from, Timestamp to, Integer id, Integer price){
        List<Schedule> schedules = scheduleRepository.findRoomBetweenDate(from,to)
                .stream()
                .filter(schedule -> schedule.getId().equals(id))
                .peek(schedule -> schedule.setPrice(price))
                .collect(toList());
        schedules.forEach(schedule -> update(schedule));
        return schedules;
    }

    @Override
    public List<Schedule> changePriceForDate(Timestamp date, Integer id, Integer price){
        return changePriceBetweenTwoDates(date,date,id,price);
    }

    @Override
    public List<Schedule> getRoomBetweenDates(Timestamp from, Timestamp to){
        return scheduleRepository.findRoomBetweenDate(from,to);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            scheduleRepository.delete(checkObjectExistsById(id, scheduleRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Schedule> findAll(Pageable pageable) {
        List<Schedule> schedules = scheduleRepository.findAll(pageable).getContent();
        return schedules;
    }

    @Override
    public ScheduleByPages findAllByAvailable(Pageable pageable) {
        LOGGER.info(">>> Page number:" + pageable.getPageNumber());
        LOGGER.info(">>> Page size:" + pageable.getPageSize());
        List<ScheduleDto> scheduleList = scheduleRepository
                .findAllByAvailable(true, pageable)
                .getContent()
                .stream()
                .map(schedule -> map(schedule, ScheduleDto.class))
                .collect(toList());
        LOGGER.info("-------------Schedule Page---------------");
        scheduleList.stream().forEach(n -> LOGGER.info(n.getId()));
        LOGGER.info("-----------------------------------------");
        scheduleList.stream().forEach(n -> LOGGER.info(n.getId()));
        return new ScheduleByPages()
                .setShedules(scheduleList)
                .setCurrentPage(pageable.getPageNumber())
                .setNumberOfItems(pageable.getPageSize())
                .setNumberOfPages((scheduleRepository
                .countAllByAvailable(true) / pageable.getPageSize()) + 1);
    }

    @Override
    public List<Schedule> findAllByAvailableAndTodayAfterAndRoomType(Timestamp today, RoomType roomType) {
//        return scheduleRepository.findAllByAvailableAndTodayAfterAndTodayBeforeAndRoomType(true, today, Timestamp.valueOf(today.toLocalDateTime().plusMonths(1L)), roomType);
        // TODO: 22/10/2018 write it later
        List<Schedule> schedules = new ArrayList<>();
        return schedules;
    }

    @Override
    public List<Schedule> findByParamForBook(Timestamp dateIn, Timestamp dateOut, RoomType roomType){
//        return scheduleRepository.findRoomBetweenDateWithRoomType(dateIn,dateOut,roomType);
        // TODO: 22/10/2018 write it later
        List<Schedule> schedules = new ArrayList<>();
        return schedules;
    }

    @Override
    public Boolean runBySchedule(){
        return true;
    }

}
