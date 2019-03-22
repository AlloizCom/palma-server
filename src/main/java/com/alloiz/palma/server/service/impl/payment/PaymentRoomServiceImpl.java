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
        return paymentRoomRepository.save(obj.setAvailable(true));
    }

    @Override
    public List<Room> findAllAvailableByType(RoomType roomType) {
        return paymentRoomRepository.findAllByAvailableAndRoomType(true,roomType);
    }

    @Override
    public List<Room> findAllByTypeWithDatesAndPlaces(RoomType roomType, Timestamp dateFrom, Timestamp dateTo,
                                                      Integer places) {

        List<com.alloiz.palma.server.model.payment.Book> books = paymentBookService.findAllAvailableByRoomType(roomType);
        books = books
                .stream()
                .filter(book ->
                        (book.getDateFrom().after(dateFrom) && book.getDateTo().before(dateFrom)) ||
                                (book.getDateTo().before(dateTo)&& book.getDateTo().after(dateFrom) )
                ).collect(Collectors.toList());

        if(books.isEmpty()||books == null)
            return findAllAvailableByType(roomType).stream().filter(room ->room.getAdditionalPlaces()>=places)
                    .collect(Collectors.toList());

        List<Room> allrooms = findAllAvailableByType(roomType)
                .stream()
                .filter(room ->room.getAdditionalPlaces()>=places)
                .collect(Collectors.toList());
        books.forEach(book -> book.getRooms().forEach(allrooms::remove));

        return allrooms;
    }

    @Override
    public List<Room> findAllByDatesAndPlaces(Timestamp dateFrom, Timestamp dateTo, Integer places) {

        LOGGER.info("DateIn "+dateFrom);
        LOGGER.info("DateTo "+dateTo);
        List<com.alloiz.palma.server.model.payment.Book> books = paymentBookService.findAllAvailable();
        books = books
                .stream()
                .filter(book ->
                        (((book.getDateFrom().after(dateFrom) && book.getDateTo().before(dateFrom)) ||
                                ((book.getDateTo().before(dateTo)&& book.getDateTo().after(dateFrom))))
                        && (!book.getDateFrom().equals(dateFrom)&& !book.getDateTo().equals(dateTo)))
                ).collect(Collectors.toList());

        LOGGER.info("books empty "+ books.isEmpty());
        LOGGER.info("books null " + (books == null));
        if(books.isEmpty()||books == null)
            return findAllAvailable().stream().filter(room ->room.getAdditionalPlaces()>=places)
                    .collect(Collectors.toList());

        List<Room> allrooms = findAllAvailable()
                .stream()
                .filter(room ->room.getAdditionalPlaces()>=places)
                .collect(Collectors.toList());
        books.forEach(book -> book.getRooms().forEach(allrooms::remove));

        LOGGER.info("AllRooms "+ allrooms.size());
        allrooms.forEach(LOGGER::info);
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
