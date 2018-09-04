package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Room;
import com.alloiz.palma.server.model.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findAllByAvailable(Boolean available);

    Room findByAvailableAndId(Boolean available, Long id);

    List<Room> findAllByAvailableAndType (Boolean available, RoomType roomType);

    List<Room> findAllByAdultPlacesAndKidsPlacesAndAvailable (Boolean available,
                                                              Integer kidsPlaces,
                                                              Integer adultPlaces);

    List<Room> findAllByAdultPlacesAndKidsPlacesAndAmountAndAvailable(Boolean available,
                                                                      Integer kidsPlaces,
                                                                      Integer adultPlaces,
                                                                      Integer amount);
}
