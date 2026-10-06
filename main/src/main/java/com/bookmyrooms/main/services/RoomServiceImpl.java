package com.bookmyrooms.main.services;

import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.entities.Property;
import com.bookmyrooms.main.entities.Room;
import com.bookmyrooms.main.exceptions.DuplicateException;
import com.bookmyrooms.main.repository.PropertyRepository;
import com.bookmyrooms.main.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService{
    private final ModelMapper modelMapper;
    private final PropertyRepository propertyRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(ModelMapper modelMapper, PropertyRepository propertyRepository, RoomRepository roomRepository) {
        this.modelMapper = modelMapper;
        this.propertyRepository = propertyRepository;
        this.roomRepository = roomRepository;
    }



    @Override
    @Transactional
    public Page<RoomDto> getAllRooms(Pageable pageable) {
//        Page<Room> rooms = this.roomRepository.findAll(pageable);
//        Page<RoomDto> roomDtos = rooms.map(
//                room -> this.modelMapper.map(room, RoomDto.class)
//        );
//        return roomDtos;

        return this.roomRepository.getAllRooms(pageable);
    }

    @Override
    public boolean existsByPropertyPropertyIdAndRoomNumber(Long propertyId, Long roomId) {
        return this.roomRepository.existsByPropertyPropertyIdAndRoomNumber(propertyId, roomId);
    }
}
