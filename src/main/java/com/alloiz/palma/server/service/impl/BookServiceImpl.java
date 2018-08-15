package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.repository.BookRepository;
import com.alloiz.palma.server.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findOneAvailable(Long id) {
        checkId(id);
        return bookRepository.findByAvailableAndId(true,id);
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
    public Book save(Book book) {
        checkSave(book);
        return bookRepository.save(book.setAvailable(true));
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
}
