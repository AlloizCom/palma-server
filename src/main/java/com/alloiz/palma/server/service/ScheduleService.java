package com.alloiz.palma.server.service;

import com.alloiz.palma.server.dto.ScheduleByPages;
import com.alloiz.palma.server.model.Schedule;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.repository.utils.ChangeRoomForSale;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleService {

    Schedule findOneAvailable(Long id);

    List<Schedule> findAllAvailable();

    List<Schedule> findAllArchived();

    Schedule findOne(Long id);

    List<Schedule> findAll();

    Schedule save(Schedule schedule);

    Schedule saveDefault(Schedule schedule);

    Schedule update(Schedule schedule);

    Schedule updateAfterBooking(Schedule schedule);

    List<Schedule> changePriceBetweenTwoDates(Timestamp from, Timestamp to, Integer id, Integer price);

    List<Schedule> changePriceForDate(Timestamp date, Integer id, Integer price);

    List<Schedule> getRoomBetweenDates(Timestamp from, Timestamp to);

    Boolean delete(Long id);

    List<Schedule> findAll(Pageable pageable);

    ScheduleByPages findAllByAvailable(Pageable pageable);

    List<Schedule> findAllByAvailableAndTodayAfterAndRoomType(Timestamp today, RoomType roomType);

    List<Schedule> findByParamForBook(Timestamp dateIn, Timestamp dateOut, RoomType roomType);

    Boolean runBySchedule();

}
