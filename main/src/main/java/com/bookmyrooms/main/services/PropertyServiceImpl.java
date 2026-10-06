package com.bookmyrooms.main.services;

import com.bookmyrooms.main.dtos.NearbyPropertyDto;
import com.bookmyrooms.main.dtos.PropertyDto;
import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.entities.Property;
import com.bookmyrooms.main.entities.Room;
import com.bookmyrooms.main.exceptions.DuplicateException;
import com.bookmyrooms.main.exceptions.ResourceNotFoundException;
import com.bookmyrooms.main.repository.PropertyRepository;
import jakarta.transaction.Transactional;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tools.jackson.databind.annotation.JsonAppend;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService{

    private final GeometryFactory geometryFactory = new GeometryFactory();
    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;
    private final RoomService roomService;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, ModelMapper modelMapper, RoomService roomService) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
        this.roomService = roomService;
    }

    @Override
    public Page<NearbyPropertyDto> getPropertiesInRadius(double lon, double lat, Pageable pageable) {
        return propertyRepository.findPropertiesWithinRadius(lon, lat, pageable);
    }

    @Override
    public PropertyDto saveProperty(PropertyDto propertyDto){
        Point point = geometryFactory.createPoint(new Coordinate(propertyDto.getLon(), propertyDto.getLat()));
        Property property = modelMapper.map(propertyDto, Property.class);
        property.setLocation(point);
        Property savedProperty =  propertyRepository.save(property);
        return toPropertyDto(savedProperty);
    }


    @Override
    public Page<PropertyDto> getProperties(Pageable pageable) {
        Page<Property> properties =  this.propertyRepository.findAll(pageable);

        Page<PropertyDto> propertyDtos = properties.map(this::toPropertyDto);
        return propertyDtos;
    }

    @Override
    public PropertyDto getPropertyById(Long propertyId) {
        Property property = this.getById(propertyId);
        return toPropertyDto(property);
    }


    @Override
    public boolean deleteProperty(Long propertyId) {
        if(this.propertyRepository.existsByPropertyId(propertyId)){
            this.propertyRepository.deleteById(propertyId);
            return true;
        }

        throw new ResourceNotFoundException("property","id",propertyId.toString());
    }


    // add room
    @Override
    @Transactional
    public RoomDto addRoom(Long propertyId, RoomDto roomDto) {
        if(this.roomService.existsByPropertyPropertyIdAndRoomNumber(propertyId, roomDto.getRoomNumber())){
            throw new DuplicateException("Room", "Room Number", roomDto.getRoomNumber().toString());
        }
        Room room = modelMapper.map(roomDto, Room.class);
        Property property = this.getById(propertyId);
        property.addRoom(room);

        this.propertyRepository.save(property);
        return modelMapper.map(room, RoomDto.class);
    }


    // method to convert Property to PropertyDto
    private PropertyDto toPropertyDto(Property property){
        PropertyDto dto = modelMapper.map(property, PropertyDto.class);
        dto.setNoOfRooms(this.propertyRepository.getNumberOfRooms(property.getPropertyId()));

        // if the location is not null then only try to convert point to longitude and latitude
        if(property.getLocation() != null){
            dto.setLon(property.getLocation().getX());
            dto.setLat(property.getLocation().getY());
        }

        return dto;
    }

    private Property getById(Long propertyId){
        Optional<Property> optional = this.propertyRepository.findById(propertyId);
        Property property = optional.isPresent()? optional.get() : null;
        return property;
    }
}
