package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Book;

import java.util.List;

public interface BookService {
    Book findOneAvailable(Long id);

    List<Book> findAllAvailable();

    Book findOne(Long id);

    List<Book> findAll();

    Book save(Book book);

    Book update(Book update);

    Boolean delete(Long id);
}
