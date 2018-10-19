package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.RoomForSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomForSaleRepository extends JpaRepository<RoomForSale,Long> {

    List<RoomForSale> findAllByAvailable(Boolean available);

    RoomForSale findByAvailableAndId(Boolean available, Long id);

}
