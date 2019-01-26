package com.alloiz.palma.server.service.impl.payment;

import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.payment.Room;
import com.alloiz.palma.server.repository.payment.PaymentRoomRepository;
import com.alloiz.palma.server.service.payment.PaymentRoomService;
import com.alloiz.palma.server.service.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentRoomServiceImpl implements PaymentRoomService
{

    @Autowired
    private PaymentRoomRepository paymentRoomRepository;

    @Override
    public Room save(Room obj) {
        Validation.checkSave(obj);
        return paymentRoomRepository.save(obj);
    }

    @Override
    public Room update(Room obj) {
        Validation.checkId(obj.getId());
        return save( findOne(obj.getId())
                .setName(obj.getName())
                .setRoomType(obj.getRoomType())
                .setRoomNumber(obj.getRoomNumber())
                .setAdditionalPlaces(obj.getAdditionalPlaces())
                .setPrice(obj.getPrice())
                .setImage(obj.getImage())
                .setAvailable(obj.getAvailable())
        );
    }

    @Override
    public Boolean delete(Room obj) {
        Validation.checkId(obj.getId());
        try{
           paymentRoomRepository.delete(obj);
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
            paymentRoomRepository.delete(id);
            return true;
        }
        catch (Exception e )
        {
            return false;
        }
    }

    @Override
    public List<Room> findAllAvailable() {
        return paymentRoomRepository.findAllByAvailable(true);
    }

    @Override public List<Room> findAllByType(RoomType roomType)
    {
        return paymentRoomRepository.findAllByRoomType(roomType);
    }

    @Override
    public Room findOne(Long id) {

        return paymentRoomRepository.findOne(id);
    }

    @Override
    public List<Room> findAll() {
        return paymentRoomRepository.findAll();
    }
}
