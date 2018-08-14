package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Order;

import java.util.List;

public interface OrderService {
    Order findOneAvailable(Long id);

    List<Order> findAllAvailable();

    Order findOne(Long id);

    List<Order> findAll();

    Order save(Order order);

    Order update(Order update);

    Boolean delete(Long id);
}
