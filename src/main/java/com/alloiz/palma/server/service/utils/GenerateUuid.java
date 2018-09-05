package com.alloiz.palma.server.service.utils;

import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class GenerateUuid {

    @Autowired
    private BookService bookService;

    public static Book generateUuid(Book book){
        return book.setUuid(generateUuid());
    }

    public static String generateUuid(){
        return UUID.randomUUID().toString();
    }

}
