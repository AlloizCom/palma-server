package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Bin;
import com.alloiz.palma.server.model.Book;

public interface PayService {

    String getButton(Book book);

    String getButton(Bin bin);

    Integer setPrice(Book book);

    Boolean checkSuccess(String data);

    void revertPayment(String orderID);

    void completePayment(String orderID);

}
