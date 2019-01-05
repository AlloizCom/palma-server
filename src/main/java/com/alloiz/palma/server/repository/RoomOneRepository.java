package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.RoomOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomOneRepository extends JpaRepository<RoomOne, Long> {

    List<RoomOne> findAllByAvailable(Boolean available);

    RoomOne findByAvailableAndId(Boolean available, Long id);

}
