package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    List<Amenity> findAllByAvailable(Boolean available);

    Amenity findByAvailableAndId(Boolean available, Long id);
}
