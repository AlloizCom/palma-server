package com.alloiz.palma.server.service;

import com.alloiz.palma.server.dto.BookByPage;
import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.enums.Language;
import com.alloiz.palma.server.model.enums.OrderStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Book findOneAvailable(Long id);

    List<Book> findAllAvailable();

    Book findOne(Long id);

    List<Book> findAll();

    Book save(Book book, Language language);

    Book update(Book update);

    Boolean delete(Long id);

    Book findByUuid(String uuid);

    List<Book> findAll(Pageable pageable);

    BookByPage findAllByAvailable(Pageable pageable);

}
