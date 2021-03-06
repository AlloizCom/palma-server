package com.alloiz.palma.server.service.impl.payment;

import com.alloiz.palma.server.exceptions.OutOfBookingNumberException;
import com.alloiz.palma.server.model.Schedule;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.payment.Book;
import com.alloiz.palma.server.model.payment.Room;
import com.alloiz.palma.server.repository.payment.PaymentBookRepository;
import com.alloiz.palma.server.service.BookCounterService;
import com.alloiz.palma.server.service.ScheduleService;
import com.alloiz.palma.server.service.payment.ClientService;
import com.alloiz.palma.server.service.payment.PaymentBookService;
import com.alloiz.palma.server.service.payment.PaymentRoomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class PaymentBookServiceImpl implements PaymentBookService
{

    private static final Logger LOGGER = Logger.getLogger(PaymentBookServiceImpl.class);
    @Autowired
    private PaymentBookRepository paymentBookRepository;

    @Autowired
    private PaymentRoomService paymentRoomService;

    @Autowired
    private BookCounterService bookCounterService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ClientService clientService;

    @Override
    public Book findOne(Long id) {
        checkId(id);
        return paymentBookRepository.findOne(id);
    }

    @Override
    public Book findOneAvailable(Long id) {
        checkId(id);
        return paymentBookRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Book> findAllAvailable() {
        return paymentBookRepository.findAllByAvailable(true);
    }


    @Override
    public List<Book> findAll() {
        return paymentBookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        checkSave(book);
        LOGGER.info(book);


        for (Room room:
              book.getRooms()) {
            if(!checkSchedule(room.getRoomType(),book.getDateFrom(),book.getDateTo())) {
                throw new OutOfBookingNumberException();
            }

        }
        for (Room room:
                book.getRooms()) {
            bookSchedule(room.getRoomType(),book.getDateFrom(),book.getDateTo()); // TODO write try catch for this and on controller

        }

        book.setClient(clientService.save(book.getClient()));

        bookCounterService.incrementCounter(
                bookCounterService.getActiveCounter()
                        .getId()
        );
        return paymentBookRepository.save(book.setAvailable(true));
    }

    @Override
    public Book update(Book book) {
        checkObjectExistsById(book.getId(), paymentBookRepository);
        LOGGER.info(book);
        return paymentBookRepository.save(findOne(book.getId())
                .setBookingDate(book.getBookingDate())
                .setBoughtOnLine(book.getBoughtOnLine())
                .setCash(book.getCash())
                .setClient(book.getClient())
                .setDateTo(book.getDateTo())
                .setDateFrom(book.getDateFrom())
                .setPayedPrice(book.getPayedPrice())
                .setStatus(book.getStatus())
                .setTotalPrice(book.getTotalPrice())
                .setAvailable(book.getAvailable()));
    }

    @Override
    public List<Book> findAllAvailableByRoomType(RoomType roomType) {
        List<Room> rooms = new ArrayList<>();
        List<Book> books  = findAllAvailable();
        books
                .stream()
                .peek(
                        book -> {
                            book.getRooms().stream().peek(room -> rooms.add(paymentRoomService.findOne(room.getId())));
                            book.setRooms(rooms);
                            rooms.clear();
                            }
                );
        for (Iterator<Book> b = books.iterator();b.hasNext();) {
            List<Room> roomsB = b.next().getRooms();
            Boolean contained = false;
            for (Room r:roomsB) {
                if(r.getRoomType() == roomType)
                    contained = true;
            }
            if (!contained){
                b.remove();
            }
        }

        return books;
    }



    @Override
    public Boolean delete(Long id) {
        LOGGER.info(">>> " + id);
        checkId(id);
        try {
            paymentBookRepository.delete(checkObjectExistsById(id, paymentBookRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Book obj) {
        Long id = obj.getId();
        LOGGER.info(">>> " + id);
        checkId(id);
        try {
            paymentBookRepository.delete(checkObjectExistsById(id, paymentBookRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkSchedule(RoomType roomType, Timestamp dateIn, Timestamp dateOut)
    {
        boolean isAvailable = true;

        List<Schedule> schedules = scheduleService.findByParamForBook(dateIn,dateOut,roomType);

        for (Schedule schedule : schedules) {
            if(schedule.getFree()<1)
                isAvailable = false;
        }

        return isAvailable;
    }

    private boolean bookSchedule(RoomType roomType, Timestamp dateIn, Timestamp dateOut){

        try {
            List<Schedule> schedules = scheduleService.findByParamForBook(dateIn, dateOut, roomType);

            for (Schedule schedule : schedules) {
                scheduleService.save(schedule.setFree(schedule.getFree()-1).setActive(schedule.getActive()+1));
            }
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

}

