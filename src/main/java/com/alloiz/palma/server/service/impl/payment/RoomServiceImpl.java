package com.alloiz.palma.server.service.impl.payment;

import com.alloiz.palma.server.model.payment.Room;
import com.alloiz.palma.server.repository.payment.RoomRepository;
import com.alloiz.palma.server.service.payment.RoomService;
import com.alloiz.palma.server.service.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room save(Room obj) {
        Validation.checkSave(obj);
        return roomRepository.save(obj);
    }

    @Override
    public Room update(Room obj) {
        Validation.checkId(obj.getId());
        return save( findOne(obj.getId())
                .setName(obj.getName())
                .setRoomType(obj.getRoomType())
                .setRoomNumber(obj.getRoomNumber())
                .setAdditionalPlaces(obj.getAdditionalPlaces())
        );
    }

    @Override
    public Boolean delete(Room obj) {
        Validation.checkId(obj.getId());
        try{
           roomRepository.delete(obj);
           return true;
        }
        catch (Exception e )
        {
            return false;
        }
    }

    @Override
    public Boolean delete(Long id) {
        Validation.checkId(id);
        try{
            roomRepository.delete(id);
            return true;
        }
        catch (Exception e )
        {
            return false;
        }
    }

    @Override
    public Room findOne(Long id) {

        return roomRepository.findOne(id);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}
