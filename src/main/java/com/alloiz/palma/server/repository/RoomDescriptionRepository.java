package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.RoomDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDescriptionRepository extends JpaRepository<RoomDescription, Long> {
    List<RoomDescription> findAllByAvailable(Boolean available);

    RoomDescription findByAvailableAndId(Boolean available, Long id);
}
