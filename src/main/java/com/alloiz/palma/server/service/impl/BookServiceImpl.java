package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.dto.BookByPage;
import com.alloiz.palma.server.dto.BookDto;
import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.enums.Language;
import com.alloiz.palma.server.model.enums.OrderStatus;
import com.alloiz.palma.server.repository.BookRepository;
import com.alloiz.palma.server.service.BookCounterService;
import com.alloiz.palma.server.service.BookService;
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
    public Book save(Book book, Language language) {
        checkSave(book);
        bookCounterService.incrementCounter(1L);
        LOGGER.info("Book service:" + book);
        Integer amountFromBook = book.getAmountOfRooms();
        scheduleService.findByParamForBook(book.getDateIn(), book.getDateOut(), book.getRoomType())
                .stream()
                .peek(schedule -> schedule.setActive(schedule.getActive() + amountFromBook))
                .peek(schedule -> schedule.setFree(schedule.getForSale() - schedule.getActive()))
                .forEach(schedule -> scheduleService.updateAfterBooking(schedule));
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
     * @param pageable
     * @return
     */
    @Override
    public List<Book> findAll(Pageable pageable) {
        List<Book> schedules = bookRepository.findAll(pageable).getContent();
        return schedules;
    }

    /**
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
    public Book cancelBook(Book book){
        checkObjectExistsById(book.getId(),bookRepository);
        LOGGER.info("Book service 'CANCEL':" + book);
        Integer amountFromBook = book.getAmountOfRooms();
        scheduleService.findByParamForBook(book.getDateIn(), book.getDateOut(), book.getRoomType())
                .stream()
                .peek(schedule -> schedule.setActive(schedule.getActive() - amountFromBook))
                .peek(schedule -> schedule.setFree(schedule.getForSale() - schedule.getActive()))
                .forEach(schedule -> scheduleService.updateAfterBooking(schedule));
        return bookRepository.save(generateUuid(book
                .setAvailable(true)
                .setOrderStatus(OrderStatus.CANCELED)
        ));
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
