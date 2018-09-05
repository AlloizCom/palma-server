package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Book;

public interface PayService {

    //todo add entity
    String getButton(Book book);

    //todo add entity
    void buy();

    Boolean checkSuccess(String data);

    void revertPayment(String orderID);

    void completePayment(String orderID);

}
