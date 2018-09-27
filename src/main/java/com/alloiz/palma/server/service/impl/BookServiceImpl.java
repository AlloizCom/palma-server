package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.dto.BookByPage;
import com.alloiz.palma.server.dto.BookDto;
import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.Schedule;
import com.alloiz.palma.server.model.enums.OrderStatus;
import com.alloiz.palma.server.repository.BookRepository;
import com.alloiz.palma.server.repository.RoomRepository;
import com.alloiz.palma.server.service.BookCounterService;
import com.alloiz.palma.server.service.BookService;
import com.alloiz.palma.server.service.RoomService;
import com.alloiz.palma.server.service.ScheduleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;
import static com.alloiz.palma.server.service.utils.GenerateUuid.generateUuid;
import static com.alloiz.palma.server.service.utils.Validation.*;
import static java.util.stream.Collectors.toList;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private BookCounterService bookCounterService;


    @Override
    public Book findOneAvailable(Long id) {
        checkId(id);
        return bookRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Book> findAllAvailable() {
        return bookRepository.findAllByAvailableOrderByBookingDayDesc(true);
    }

    @Override
    public Book findOne(Long id) {
        checkId(id);
        return bookRepository.findOne(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
//        checkSave(book);
//        List<Room> rooms = roomRepository.findAllByAvailableAndType(true, book.getRoomType());
//        try {
//            Room resRoom = rooms.stream()
//                    .filter(room -> room.getType().equals(book.getRoomType()))
//                    .map(room -> room.setAmount(room.getAmount() - book.getAmountOfRooms()))
//                    .findFirst().orElseThrow(Exception::new);
//            if(resRoom.getAmount()<0){
//                throw new Exception();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (Room room : rooms) {
//            if (room.getType().equals(book.getRoomType())) {
//                if (room.getAmount() >= book.getAmountOfRooms()) {
//                    room.setAmount(room.getAmount() - book.getAmountOfRooms());
//                }
//            }
//        }


//        checkSave(book);
//        List<Room> rooms = roomRepository.findAllByAvailableAndType(true, book.getRoomType());
//        for (Room room : rooms) {
//            if (room.getType().equals(book.getRoomType())) {
//                if (room.getAmount() >= book.getAmountOfRooms()) {
//                    room.setAmount(room.getAmount() - book.getAmountOfRooms());
//                    roomService.changeAmount(room.getType(), book.getAmountOfRooms());
//                } else {
//                    throw new NotEnoughFreePlacesException("Not Enough Free Places !");
//                }
//            } else {
//                throw new RoomTypeNotFoundException("Room Type Not Found !");
//            }
//        }

//        checkSave(book);
//        List<Schedule> scheduleForUpdate = scheduleService.findByParamForBook(book.getDateIn(),
//                                                                            book.getDateOut());
//        for (Schedule schedule: scheduleForUpdate
//             ) {
//            scheduleService.update(schedule.setFree(schedule.getFree() - book.getAmountOfRooms()));
//        }

        checkSave(book);
        bookCounterService.incrementCounter(1L);
        LOGGER.info("Book service:" + book);
//        scheduleService.findByParamForBook(book.getDateIn(),book.getDateOut())
//                .stream()
//                .filter(schedule -> schedule.getRoomType().equals(book.getRoomType()))
//                .forEach(schedule -> scheduleService.update(schedule.setFree(
//                        schedule.getFree() - book.getAmountOfRooms()
//                )));

        List<Schedule> schedules = scheduleService.findByParamForBook(book.getDateIn(),book.getDateOut(),book.getRoomType());
        for (Schedule s: schedules
             ) {
            LOGGER.warn(s);
        }

        return bookRepository.save(generateUuid(book
                .setAvailable(true)
                .setBookingDay(Timestamp.valueOf(LocalDateTime.now()))
        ));
    }

    @Override
    public Book update(Book book) {
        checkObjectExistsById(book.getId(), bookRepository);
        return bookRepository.save(findOne(book.getId())
                .setOrderStatus(book.getOrderStatus())
                .setAvailable(book.getAvailable()));
    }

    @Override
    public Boolean delete(Long id) {
        try {
            bookRepository.delete(checkObjectExistsById(id, bookRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Book findByUuid(String uuid) {
        checkString(uuid);
        return bookRepository.findByUuid(uuid);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @Override
    public List<Book> findAll(Pageable pageable) {
        List<Book> schedules = bookRepository.findAll(pageable).getContent();
        return schedules;
    }

    /**
     *
     * @param pageable
     * @return
     */
    @Override
    public BookByPage findAllByAvailable(Pageable pageable) {
        LOGGER.info(">>> Page number:" + pageable.getPageNumber());
        LOGGER.info(">>> Page size:" + pageable.getPageSize());
        List<BookDto> bookList = bookRepository
                .findAllByAvailableOrderByBookingDayDesc(true, pageable)
                .getContent()
                .stream()
                .map(book -> map(book, BookDto.class))
                .collect(toList());
        LOGGER.info("-------------Book Page---------------");
        bookList.stream().forEach(bookDto -> LOGGER.info(bookDto.getId()));
        return new BookByPage()
                .setBooks(bookList)
                .setCurrentPage(pageable.getPageNumber())
                .setNumberOfItems(pageable.getPageSize())
                .setNumberOfPages((bookRepository
                        .countAllByAvailable(true) / pageable.getPageSize()) + 1);
    }

    @Override
    public Book changeStatus(Long id, OrderStatus orderStatus) {
        Book book = findOne(id);
        book.setOrderStatus(orderStatus);
        return bookRepository.save(findOne(book.getId())
                .setOrderStatus(book.getOrderStatus())
                .setAvailable(book.getAvailable())
                .setBookingDay(book.getBookingDay())
                .setUuid(book.getUuid())
                .setEmail(book.getEmail())
                .setAdults(book.getAdults())
                .setDateIn(book.getDateIn())
                .setDateOut(book.getDateOut())
                .setFirstName(book.getFirstName())
                .setLastName(book.getLastName())
                .setKids(book.getKids())
                .setPhoneNumber(book.getPhoneNumber())
                .setMessage(book.getMessage())
        );
    }
}
