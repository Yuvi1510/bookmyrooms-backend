package com.bookmyrooms.main.services;

import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.entities.Room;
import org.springframework.data.domain.Page;

public interface RoomService {
    boolean addRoom(Long propertyId, RoomDto roomDto);
    Page<Room> getAllRooms();

}
