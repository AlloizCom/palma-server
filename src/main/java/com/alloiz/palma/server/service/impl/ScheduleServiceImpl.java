package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Schedule;
import com.alloiz.palma.server.repository.ScheduleRepository;
import com.alloiz.palma.server.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

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
    public Schedule findOne(Long id) {
        checkId(id);
        return scheduleRepository.findOne(id);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> findAllByDateAndPlaces(Timestamp today, Integer places){
        return scheduleRepository
                .findAllByTodayAndForSaleAndAvailable(today,places,true);
    }

    @Override
    public List<Schedule> findByParam(Timestamp today, Integer places){
        return scheduleRepository.findByTodayAndForSale(today,places);
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
                    .setForSale(schedule.getForSale())
                    .setActive(schedule.getActive())
                    .setFree(schedule.getForSale() - schedule.getActive()));
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
    public Boolean runBySchedule(){
        return true;
    }
}
