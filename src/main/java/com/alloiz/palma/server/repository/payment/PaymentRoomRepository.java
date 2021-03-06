package com.alloiz.palma.server.repository.payment;

import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.payment.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentRoomRepository extends JpaRepository<Room, Long>
{
    List<Room> findAllByAvailable(Boolean available);

    List<Room> findAllByRoomType(RoomType roomType);

    List<Room> findAllByAvailableAndRoomType(Boolean available, RoomType roomType);
}
