package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Schedule;
import com.alloiz.palma.server.model.enums.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    List<Schedule> findAllByAvailable(Boolean available);

    Schedule findByAvailableAndId(Boolean available, Long id);

    List<Schedule> findAllByTodayAndForSaleAndAvailable (Timestamp today,
                                                         Integer forSale,
                                                         Boolean available
                                                         );

    Page<Schedule> findAllByAvailable (Boolean available, Pageable pageable);

    Page<Schedule> findAll(Pageable pageable);

    Integer countAllByAvailable(Boolean available);

    @Query("SELECT s FROM Schedule s " +
    "WHERE s.free >= :free " +
            "AND s.today = :today"
    )
    List<Schedule> findByTodayAndForSale (@Param("today") Timestamp today,
                                          @Param("free")Integer free
    );

    /**
     * Find all schedule with date between dateIn and dateOut and free places from Book
     * @param dateIn
     * @param dateOut
     * @param free
     * @return List<Schedule>
     */
    @Query("SELECT s FROM Schedule s " +
            "WHERE s.today BETWEEN :dateIn AND :dateOut " +
            "AND s.free >= :free"
    )
        List<Schedule> findRoomByDate (@Param("dateIn") Timestamp dateIn,
                                       @Param("dateOut") Timestamp dateOut,
                                   @Param("free") Integer free
    );

    @Query("SELECT s FROM Schedule s " +
            "WHERE s.today BETWEEN :dateIn AND :dateOut "
    )
    List<Schedule> findRoomByDateinAndDateOut (@Param("dateIn") Timestamp dateIn,
                                   @Param("dateOut") Timestamp dateOut);

    /**
     * Find all schedule with date between dateIn and dateOut and free places from Book
     * @param dateIn
     * @param dateOut
     * @return List<Schedule>
     */
//    @Query("SELECT s FROM Schedule s " +
//            "WHERE s.today BETWEEN :dateIn AND :dateOut"
//    )
//    List<Schedule> findRoomBetweenDateWithRoomType (@Param("dateIn") Timestamp dateIn,
//                                   @Param("dateOut") Timestamp dateOut
//    );

    @Query("SELECT s FROM Schedule s " +
            "WHERE s.today >= :dateIn " +
            "AND s.today <= :dateOut " +
            "AND s.roomType = :roomType"
    )
    List<Schedule> findRoomBetweenDateWithRoomType(@Param("dateIn") Timestamp dateIn,
                                                   @Param("dateOut") Timestamp dateOut,
                                                   @Param("roomType") RoomType roomType
    );

    @Query("SELECT s FROM Schedule s " +
            "WHERE s.today = :yesterday "
    )
    List<Schedule> findByTodayDate (@Param("yesterday") Timestamp yesterday);

    @Query("SELECT s FROM Schedule s " +
            "WHERE s.roomType = :roomType "
    )
    List<Schedule> findAllByRoomType(@Param("roomType") RoomType roomType);

    List<Schedule> findAllByAvailableAndTodayAfterAndTodayBeforeAndRoomType(Boolean available, Timestamp today, Timestamp today2, RoomType roomType);

}
