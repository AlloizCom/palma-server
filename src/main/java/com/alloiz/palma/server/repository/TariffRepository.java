package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TariffRepository extends JpaRepository<Tariff,Long> {

    List<Tariff> findAllByAvailable(Boolean available);

    Tariff findByAvailableAndId(Boolean available, Long id);
}
