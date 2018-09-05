package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Tariff;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.enums.TariffType;

import java.sql.Timestamp;
import java.util.List;

public interface TariffService {

    Tariff findOneAvailable(Long id);

    List<Tariff> findAllAvailable();

    Tariff findOne(Long id);

    List<Tariff> findByRoomType (RoomType roomType);

    List<Tariff> findByTariffType(TariffType tariffType);

    List<Tariff> findAll();

    List<Tariff> findByTariffTypeAndRoomType(TariffType tariffType, RoomType roomType);

    Tariff findByRoomTypeAndDateBetween(RoomType roomType, Timestamp date);

    /**
     * @param roomType room type
     * @return tariff with time between now and specified room type
     */
    Tariff findByRoomTypeAndDateNow(RoomType roomType);

    List<Tariff> findAllDateNow();

    List<Tariff> findAllDateBetween(Timestamp date);

    Tariff save(Tariff tariff);

    Tariff update(Tariff tariff);

    Boolean delete(Long id);
}
