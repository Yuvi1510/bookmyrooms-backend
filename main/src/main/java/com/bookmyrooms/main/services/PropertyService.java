package com.bookmyrooms.main.services;

import com.bookmyrooms.main.dtos.NearbyPropertyDto;
import com.bookmyrooms.main.dtos.PropertyDto;
import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.request.PropertyListingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PropertyService {
    PropertyDto saveProperty(PropertyListingRequest propertyListingRequest, MultipartFile files);
    Page<PropertyDto> getProperties(Pageable pageable);
    Page<NearbyPropertyDto> getPropertiesInRadius(double lon, double lat, Pageable pageable);
    PropertyDto getPropertyById(Long propertyId);
    boolean deleteProperty(Long propertyId);
    RoomDto addRoom(Long propertyId, RoomDto roomDto);
}
