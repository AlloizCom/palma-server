package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.RoomForSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomForSaleRepository extends JpaRepository<RoomForSale,Long> {

    List<RoomForSale> findAllByAvailable(Boolean available);

    RoomForSale findByAvailableAndId(Boolean available, Long id);

}
