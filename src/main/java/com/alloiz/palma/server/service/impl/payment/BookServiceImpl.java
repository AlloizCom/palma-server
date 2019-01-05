package com.alloiz.palma.server.service.impl.payment;

import com.alloiz.palma.server.model.payment.Book;
import com.alloiz.palma.server.repository.payment.BookRepository;
import com.alloiz.palma.server.service.payment.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class);
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findOne(Long id) {
        checkId(id);
        return bookRepository.findOne(id);
    }

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
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        checkSave(book);
        LOGGER.info(book);
        return bookRepository.save(book.setAvailable(true));
    }

    @Override
    public Book update(Book book) {
        checkObjectExistsById(book.getId(), bookRepository);
        LOGGER.info(book);
        return bookRepository.save(findOne(book.getId())
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
    public Boolean delete(Long id) {
        LOGGER.info(">>> " + id);
        checkId(id);
        try {
            bookRepository.delete(checkObjectExistsById(id, bookRepository));
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
            bookRepository.delete(checkObjectExistsById(id, bookRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
