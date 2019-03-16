package com.alloiz.palma.server.service.payment;

import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.payment.Room;

import java.sql.Timestamp;
import java.util.List;


public interface PaymentRoomService extends CRUDService<Room>
{

    List<Room> findAllAvailable();

    List<Room> findAllByType(RoomType roomType);

    List<Room> findAllAvailableByType(RoomType roomType);

    List<Room> findAllByTypeWithDatesAndPlaces(RoomType roomType, Timestamp dateFrom, Timestamp dateTo, Integer places);

    List<Room> findAllByDatesAndPlaces(Timestamp dateFrom, Timestamp dateTo, Integer places);
}
