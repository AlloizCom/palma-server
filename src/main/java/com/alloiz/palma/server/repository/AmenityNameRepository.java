package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.AmenityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenityNameRepository extends JpaRepository<AmenityName, Long> {

    List<AmenityName> findAllByAvailable(Boolean available);

    AmenityName findByAvailableAndId(Boolean available, Long id);

}
