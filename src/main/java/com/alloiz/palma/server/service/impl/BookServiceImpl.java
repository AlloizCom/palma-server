package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.dto.BookByPage;
import com.alloiz.palma.server.dto.BookDto;
import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.enums.Language;
import com.alloiz.palma.server.repository.BookRepository;
import com.alloiz.palma.server.service.BookCounterService;
import com.alloiz.palma.server.service.BookService;
import com.alloiz.palma.server.service.MailService;
import com.alloiz.palma.server.service.ScheduleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Autowired
    private MailService mailService;

    @Override
    public Book findOneAvailable(Long id) {
        checkId(id);
        return bookRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Book> findAllAvailable() {
        return bookRepository.findAllByAvailable(true);
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
//        Integer amountFromBook = book.getAmountOfRooms();
//        scheduleService.findByParamForBook(book.getDateIn(), book.getDateOut(), book.getRoomType())
//                .stream()
//                .forEach(schedule -> scheduleService.updateAfterBooking(schedule));
//        if (book.getOrderStatus().equals(OrderStatus.PAID_BY_CARD)){
//            book.setOrderStatus(OrderStatus.PAID_BY_CARD);
//        }
//        if (book.getOrderStatus().equals(OrderStatus.HAVE_TO_BE_PAID)){
//            book.setOrderStatus(OrderStatus.HAVE_TO_BE_PAID);
//        }
//        book.setBookingDay(Timestamp.valueOf(LocalDateTime.now()));
//        mailService.sendBookMailForStuffAndUser(book,language);
        return bookRepository.save(generateUuid(book
                .setAvailable(true)
        ));
    }

    @Override
    public Book update(Book book) {
        checkObjectExistsById(book.getId(), bookRepository);
        return bookRepository.save(findOne(book.getId())
                .setAmountOfRooms(book.getAmountOfRooms())
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
        //return bookRepository.findByUuid(uuid);
        // TODO: 23/10/2018 later
        return bookRepository.findOne(0L);
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
                .findAllByAvailable(true, pageable)
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

}
