package com.alloiz.palma.server.service.payment;

import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.payment.Room;

import java.util.List;


public interface RoomService extends CRUDService<Room>
{

    List<Room> findAllAvailable();

    List<Room> findAllByType(RoomType roomType);

}
