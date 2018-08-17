package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Tariff;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.enums.TariffType;

import java.util.List;

public interface TariffService {

    Tariff findOneAvailable(Long id);

    List<Tariff> findAllAvailable();

    Tariff findOne(Long id);

    List<Tariff> findByRoomType (RoomType roomType);

    List<Tariff> findByTariffType(TariffType tariffType);

    List<Tariff> findAll();

    List<Tariff> findByTariffTypeAndRoomType(TariffType tariffType, RoomType roomType);

    Tariff save(Tariff tariff);

    Tariff update(Tariff tariff);

    Boolean delete(Long id);
}
