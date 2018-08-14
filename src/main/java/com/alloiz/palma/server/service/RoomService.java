package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Room;

import java.util.List;

public interface RoomService {
    Room findOneAvailable(Long id);

    List<Room> findAllAvailable();

    Room findOne(Long id);

    List<Room> findAll();

    Room save(Room room);

    Room update(Room update);

    Boolean delete(Long id);
}
