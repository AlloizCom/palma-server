package com.alloiz.palma.server.service.impl.payment;

import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.payment.Room;
import com.alloiz.palma.server.repository.payment.PaymentRoomRepository;
import com.alloiz.palma.server.service.payment.PaymentBookService;
import com.alloiz.palma.server.service.payment.PaymentRoomService;
import com.alloiz.palma.server.service.utils.Validation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentRoomServiceImpl implements PaymentRoomService
{
    private static final Logger LOGGER = Logger.getLogger(PaymentRoomService.class);

    @Autowired
    private PaymentRoomRepository paymentRoomRepository;

    @Autowired
    private PaymentBookService paymentBookService;

    @Override
    public Room save(Room obj) {
        Validation.checkSave(obj);
        return paymentRoomRepository.save(obj);
    }

    @Override
    public List<Room> findAllAvailableByType(RoomType roomType) {
        return paymentRoomRepository.findAllByAvailableAndRoomType(true,roomType);
    }

    @Override
    public List<Room> findAllByTypeWithDatesAndPlaces(RoomType roomType, Timestamp dateFrom, Timestamp dateTo,
                                                      Integer places) {
        List<com.alloiz.palma.server.model.payment.Book> books = paymentBookService.findAllAvailableByRoomType(roomType);
//        LOGGER.info("dateTo " + dateTo);
//        LOGGER.info("---------------------");
//        LOGGER.info("dateFrom " + dateFrom);
        books = books
                .stream()
                .filter(book -> {
//                    LOGGER.info(book);
//                    LOGGER.info((book.getDateFrom().after(dateFrom)));
//                    LOGGER.info((book.getDateTo().before(dateTo)&& book.getDateTo().after(dateFrom) ));
//                    LOGGER.info((book.getDateFrom().after(dateFrom))|| (book.getDateTo().before(dateTo)&& book.getDateTo().after(dateFrom) ));
                    return (book.getDateFrom().after(dateFrom) && book.getDateTo().before(dateFrom))|| (book.getDateTo().before(dateTo)&& book.getDateTo().after(dateFrom) );
                })
                .collect(Collectors.toList());
        LOGGER.info("-------------- Find by dates -----------------");
        LOGGER.info("----------- after filter -------------------");
        LOGGER.info(books.size());
        if(books.isEmpty()||books == null)
            return findAllAvailableByType(roomType);

        LOGGER.info("--------- places -----------");
        LOGGER.info(places);
        LOGGER.info("--------- places -----------");
        List<Room> allrooms = findAllAvailableByType(roomType)
                .stream()
                .filter(room -> room.getAdditionalPlaces()>=places)
                .collect(Collectors.toList());
        LOGGER.info("-------------- AllRooms -----------------");
        LOGGER.info(allrooms.size());
        allrooms.forEach(LOGGER::info);
        LOGGER.info("-------------- AllRooms -----------------");
        LOGGER.info("------------ Book Rooms -----------------");


        books.forEach(book -> {
            LOGGER.info(book.getRooms().size());
            book.getRooms().forEach(LOGGER::info);
            book.getRooms()
                    .forEach(allrooms::remove);
        });
//        LOGGER.info("------------ Book Rooms -----------------");
//
        LOGGER.info("-------------- Find by dates -----------------");
        LOGGER.info(allrooms.size());
        LOGGER.info("-------------- Find by dates -----------------");


        return allrooms;
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
