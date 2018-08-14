package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findAllByAvailable(Boolean available);

    Room findByAvailableAndId(Boolean available, Long id);
}
