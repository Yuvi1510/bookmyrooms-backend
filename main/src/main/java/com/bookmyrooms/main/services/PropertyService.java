package com.bookmyrooms.main.services;

import com.bookmyrooms.main.dtos.NearbyPropertyDto;
import com.bookmyrooms.main.dtos.PropertyDto;
import com.bookmyrooms.main.entities.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PropertyService {
    Page<NearbyPropertyDto> getPropertiesInRadius(double lon, double lat, Pageable pageable);
    PropertyDto saveProperty(Property property, double lon, double lat);
    Page<PropertyDto> getProperties(Pageable pageable);
    Property getPropertyById(Long propertyId);
}
