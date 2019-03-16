package com.alloiz.palma.server.service;

import com.alloiz.palma.server.dto.ScheduleByPages;
import com.alloiz.palma.server.model.Schedule;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.repository.utils.ChangeRoomForSale;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleService {

    Schedule findOneAvailable(Long id);

    List<Schedule> findAllAvailable();

    List<Schedule> findAllArchived();

    Schedule findOne(Long id);

    List<Schedule> findAll();

    List<Schedule> findAllByDateAndPlaces(Timestamp today, Integer places);

    List<Schedule> findByParam(Timestamp today, Integer places);

    Schedule save(Schedule schedule);

    Schedule saveDefault(Schedule schedule);

    Schedule update(Schedule schedule);

    Schedule updateAfterBooking(Schedule schedule);

    Boolean delete(Long id);

    List<Schedule> findAll(Pageable pageable);

    ScheduleByPages findAllByAvailable(Pageable pageable);

    List<Schedule> findAllByAvailableAndTodayAfterAndRoomType(Timestamp today, RoomType roomType);

    List<Schedule> findByParamForBook(Timestamp dateIn, Timestamp dateOut, RoomType roomType);

    Boolean runBySchedule();

    Boolean changeRoomForSale(ChangeRoomForSale changeRoomForSale);

    List<Schedule> findRoomByDateinAndDateOut (Timestamp dateIn, Timestamp dateOut);
}
