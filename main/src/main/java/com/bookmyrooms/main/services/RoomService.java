package com.bookmyrooms.main.services;

import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {
    Page<RoomDto> getAllRooms(Pageable pageable);
    boolean existsByPropertyPropertyIdAndRoomNumber(Long propertyId, Long roomId);
}
