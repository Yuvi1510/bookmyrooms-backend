package com.bookmyrooms.main.services;

import com.bookmyrooms.main.dtos.NearbyPropertyDto;
import com.bookmyrooms.main.dtos.PropertyDto;
import com.bookmyrooms.main.entities.Property;

import java.util.List;

public interface PropertyService {
    List<NearbyPropertyDto> getPropertiesInRadius(double lon, double lat);
    PropertyDto saveProperty(Property property, double lon, double lat);
}
