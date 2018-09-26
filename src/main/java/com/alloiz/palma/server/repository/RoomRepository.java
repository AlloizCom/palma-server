package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Room;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.repository.utils.RoomParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Collection;
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

    @Query("SELECT r FROM Room r " +
            "WHERE r.adultPlaces >= :adults " +
            "AND r.kidsPlaces >= :children " +
            "AND r.type IN (SELECT s.roomType FROM Schedule s " +
                        "WHERE s.today BETWEEN :dateIn AND :dateOut " +
                        "AND s.free >= :free)")
    List<Room> findAllByTypeIn(@Param("dateIn") Timestamp dateIn,
                               @Param("dateOut") Timestamp dateOut,
                               @Param("free")Integer free,
                               @Param("adults")Integer adults,
                               @Param("children")Integer children);

    @Query("SELECT r FROM Room r " +
            "WHERE r.type >= :roomType " +
            "AND r.adultPlaces >= :adults " +
            "AND r.kidsPlaces >= :children " +
            "AND r.type IN (SELECT s.roomType FROM Schedule s " +
            "WHERE s.today BETWEEN :dateIn AND :dateOut " +
            "AND s.free >= :free)")
    List<Room> findAllByTypeInWithRoomType(@Param("dateIn") Timestamp dateIn,
                               @Param("dateOut") Timestamp dateOut,
                               @Param("free")Integer free,
                               @Param("adults")Integer adults,
                               @Param("children")Integer children,
                                           @Param("roomType") RoomType roomType
    );

}
