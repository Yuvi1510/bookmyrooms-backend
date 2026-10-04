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
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService{
    private final ModelMapper modelMapper;
    private final PropertyRepository propertyRepository;
    private final PropertyService propertyService;
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(ModelMapper modelMapper, PropertyRepository propertyRepository, PropertyService propertyService, RoomRepository roomRepository) {
        this.modelMapper = modelMapper;
        this.propertyRepository = propertyRepository;
        this.propertyService = propertyService;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public boolean addRoom(Long propertyId, RoomDto roomDto) {
        if(this.roomRepository.existsByPropertyPropertyIdAndRoomNumber(propertyId, roomDto.getRoomNumber())){
            throw new DuplicateException("Room", "Room Number", roomDto.getRoomNumber().toString());
        }
        Room room = modelMapper.map(roomDto, Room.class);
        Property property = this.propertyService.getPropertyById(propertyId);
        property.addRoom(room);

        this.propertyRepository.save(property);
        return true;
    }

    @Override
    @Transactional
    public Page<Room> getAllRooms() {
        return null;
    }
}
