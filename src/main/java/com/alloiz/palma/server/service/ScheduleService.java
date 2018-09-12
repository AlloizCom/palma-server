package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Schedule;

import java.util.List;

public interface ScheduleService {

    Schedule findOneAvailable(Long id);

    List<Schedule> findAllAvailable();

    Schedule findOne(Long id);

    List<Schedule> findAll();

    Schedule save(Schedule Schedule);

    Schedule update(Schedule update);

    Boolean delete(Long id);

}
