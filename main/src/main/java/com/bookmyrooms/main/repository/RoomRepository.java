package com.bookmyrooms.main.repository;

import com.bookmyrooms.main.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByPropertyPropertyIdAndRoomNumber(Long propertyId, Long roomNumber);
}
