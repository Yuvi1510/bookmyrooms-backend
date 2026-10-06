package com.bookmyrooms.main.repository;

import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByPropertyPropertyIdAndRoomNumber(Long propertyId, Long roomNumber);

    @Query("SELECT new com.bookmyrooms.main.dtos.RoomDto(r.roomId," +
            "r.roomNumber,r.areaInSqFt, r.noOfBeds, r.roomType, r.price, r.isAvailable, p.propertyName) " +
            "FROM Room r JOIN r.property p")
    Page<RoomDto> getAllRooms(Pageable pageable);
}
