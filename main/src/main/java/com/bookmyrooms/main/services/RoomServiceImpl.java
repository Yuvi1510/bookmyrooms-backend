package com.bookmyrooms.main.services;

import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.entities.Property;
import com.bookmyrooms.main.entities.Room;
import com.bookmyrooms.main.repository.PropertyRepository;
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

    @Autowired
    public RoomServiceImpl(ModelMapper modelMapper, PropertyRepository propertyRepository, PropertyService propertyService) {
        this.modelMapper = modelMapper;
        this.propertyRepository = propertyRepository;
        this.propertyService = propertyService;
    }

    @Override
    @Transactional
    public boolean addRoom(Long propertyId, RoomDto roomDto) {
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
