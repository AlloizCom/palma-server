package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Tariff;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.enums.TariffType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TariffRepository extends JpaRepository<Tariff,Long> {

    List<Tariff> findAllByAvailable(Boolean available);

    Tariff findByAvailableAndId(Boolean available, Long id);

    List<Tariff> findAllByAvailableAndRoomType(Boolean available, RoomType roomType);

    List<Tariff> findAllByAvailableAndTariffType (Boolean available, TariffType tariffType);

    List<Tariff> findAllByAvailableAndTariffTypeAndRoomType (Boolean available, TariffType tariffType, RoomType roomType);
}
