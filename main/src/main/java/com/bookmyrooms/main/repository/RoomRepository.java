package com.bookmyrooms.main.repository;

import com.bookmyrooms.main.entities.rooms.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByPropertyPropertyIdAndRoomNumber(Long propertyId, Long roomNumber);

    Page<Room> findRoomsByPropertyPropertyId(Long propertyPropertyId, Pageable pageable);

    //    @Query("SELECT new com.bookmyrooms.main.dtos.RoomDto(r.roomId," +
//            "r.roomNumber,r.areaInSqFt, r.beds, r.roomType, r.basePrice, r.weekendAdjustment, r.cleaningFee, r.isAvailable, p.title) " +
//            "FROM Room r JOIN r.property p")
//    Page<RoomDto> getAllRooms(Pageable pageable);
}
