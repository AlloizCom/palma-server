package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.RoomForSale;
import com.alloiz.palma.server.model.RoomForSaleDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomForSaleDescriptionRepository extends JpaRepository<RoomForSaleDescription,Long> {

    List<RoomForSale> findAllByAvailable(Boolean available);

    RoomForSale findByAvailableAndId(Boolean available, Long id);

}
