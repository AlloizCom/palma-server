package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findAllByAvailable(Boolean available);

    Schedule findByAvailableAndId(Boolean available, Long id);
}
