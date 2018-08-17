package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TariffRepository extends JpaRepository<Tariff,Long> {

    List<Tariff> findAllByAvailable(Boolean available);

    Tariff findByAvailableAndId(Boolean available, Long id);
}
