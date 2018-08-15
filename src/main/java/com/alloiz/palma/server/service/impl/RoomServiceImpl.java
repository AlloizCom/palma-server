package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Room;
import com.alloiz.palma.server.repository.RoomRepository;
import com.alloiz.palma.server.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.*;
import static com.alloiz.palma.server.config.mapper.JsonMapper.json;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room findOneAvailable(Long id) {
        checkId(id);
        return roomRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Room> findAllAvailable() {
        return roomRepository.findAllByAvailable(true);
    }

    @Override
    public Room findOne(Long id) {
        checkId(id);
        return roomRepository.findOne(id);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        checkSave(room);
        room.getDescriptions().stream().forEach(roomDescription -> roomDescription.setAvailable(true));
        roomRepository.save(room.setAvailable(true));

        return room;
    }

    @Override
    public Room save(String roomJson, MultipartFile[] multipartFiles) {
        checkJson(roomJson);
        Room room = json(roomJson, Room.class);
        return save(room);
        }

    @Override
    public Room update(Room room) {
        checkObjectExistsById(room.getId(),roomRepository);
        return roomRepository.save(findOne(room.getId())
                .setAdultPlaces(room.getAdultPlaces())
                .setAmenities(room.getAmenities())
                .setAvailable(room.getAvailable())
                .setDescriptions(room.getDescriptions())
                .setKidsPlaces(room.getKidsPlaces())
                .setSquare(room.getSquare())
                .setType(room.getType()));
    }

    @Override
    public Room update(String roomJson) {
        checkJson(roomJson);
        Room room = json(roomJson, Room.class);
        return update(room);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            roomRepository.delete(checkObjectExistsById(id, roomRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
