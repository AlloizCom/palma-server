package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Room;
import com.alloiz.palma.server.repository.RoomRepository;
import com.alloiz.palma.server.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.*;

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
        return roomRepository.save(room.setAvailable(true));
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
    public Boolean delete(Long id) {
        try {
            roomRepository.delete(checkObjectExistsById(id, roomRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
