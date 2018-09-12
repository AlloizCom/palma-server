package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Schedule;
import com.alloiz.palma.server.repository.ScheduleRepository;
import com.alloiz.palma.server.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Schedule save(Schedule Schedule) {
        return null;
    }

    @Override
    public Schedule update(Schedule update) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
