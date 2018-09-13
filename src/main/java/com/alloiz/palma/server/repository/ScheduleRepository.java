package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Schedule;
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
    @Query("SELECT s FROM Schedule s " +
    "WHERE s.free >= :free " +
            "AND s.today = :today"
    )
    List<Schedule> findByTodayAndForSale (@Param("today") Timestamp today,
                                          @Param("free")Integer free
    );
}
