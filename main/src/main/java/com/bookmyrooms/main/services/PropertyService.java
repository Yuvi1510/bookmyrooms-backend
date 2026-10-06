package com.bookmyrooms.main.services;

import com.bookmyrooms.main.dtos.NearbyPropertyDto;
import com.bookmyrooms.main.dtos.PropertyDto;
import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.entities.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PropertyService {
    PropertyDto saveProperty(PropertyDto propertyDto);
    Page<PropertyDto> getProperties(Pageable pageable);
    Page<NearbyPropertyDto> getPropertiesInRadius(double lon, double lat, Pageable pageable);
    PropertyDto getPropertyById(Long propertyId);
    boolean deleteProperty(Long propertyId);
    RoomDto addRoom(Long propertyId, RoomDto roomDto);
}
