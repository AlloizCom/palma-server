package com.alloiz.palma.server.service.payment;

import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.payment.Book;

import java.util.List;


public interface PaymentBookService extends CRUDService<Book>
{
    Book findOneAvailable(Long id);

    List<Book> findAllAvailable();

    List<Book> findAllAvailableByRoomType(RoomType roomType);
}
