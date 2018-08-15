package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Order;
import com.alloiz.palma.server.repository.OrderRepository;
import com.alloiz.palma.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findOneAvailable(Long id) {
        checkId(id);
        return orderRepository.findByAvailableAndId(true,id);
    }

    @Override
    public List<Order> findAllAvailable() {
        return orderRepository.findAllByAvailable(true);
    }

    @Override
    public Order findOne(Long id) {
        checkId(id);
        return orderRepository.findOne(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        checkSave(order);
        return orderRepository.save(order.setAvailable(true));
    }

    @Override
    public Order update(Order order) {
        checkObjectExistsById(order.getId(),orderRepository);
        return orderRepository.save(findOne(order.getId())
                .setStatus(order.getStatus())
                .setAvailable(order.getAvailable()));
    }

    @Override
    public Boolean delete(Long id) {
        try {
            orderRepository.delete(checkObjectExistsById(id, orderRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
