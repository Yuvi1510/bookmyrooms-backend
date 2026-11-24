package com.bookmyrooms.main.services;

import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.request.AddRoomRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomService {
    RoomDto addRoom(AddRoomRequest addRoomRequest, List<MultipartFile> files, Long propertyId);
    Page<RoomDto> getRoomsByPropertyId(Long propertyId, Pageable pageable);
    Page<RoomDto> getAllRooms(Pageable pageable);
    RoomDto getRoomById(Long roomId);
    boolean existsByPropertyPropertyIdAndRoomNumber(Long propertyId, Long roomId);
}
