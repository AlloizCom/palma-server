package com.alloiz.palma.server.service;

public interface PayService {

    //todo add entity
    String getButton();

    //todo add entity
    void buy();

    Boolean checkSuccess(String data);

    void revertPayment(String orderID);

    void completePayment(String orderID);

}
