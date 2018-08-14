package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByAvailable(Boolean available);

    Order findByAvailableAndId(Boolean available, Long id);
}
