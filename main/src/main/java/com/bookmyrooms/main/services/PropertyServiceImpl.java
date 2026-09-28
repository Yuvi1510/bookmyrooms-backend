package com.bookmyrooms.main.services;

import com.bookmyrooms.main.dtos.NearbyPropertyDto;
import com.bookmyrooms.main.dtos.PropertyDto;
import com.bookmyrooms.main.entities.Property;
import com.bookmyrooms.main.repository.PropertyRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService{

    private final GeometryFactory geometryFactory = new GeometryFactory();
    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<NearbyPropertyDto> getPropertiesInRadius(double lon, double lat) {
        return propertyRepository.findPropertiesWithinRadius(lon, lat);
    }

    @Override
    public PropertyDto saveProperty(Property property, double lon, double lat){
        Point point = geometryFactory.createPoint(new Coordinate(lon, lat));
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


    // method to convert Property to PropertyDto
    private PropertyDto toPropertyDto(Property property){
        PropertyDto dto = modelMapper.map(property, PropertyDto.class);

        // if the location is not null then only try to convert point to longitude and latitude
        if(property.getLocation() != null){
            dto.setLon(property.getLocation().getX());
            dto.setLat(property.getLocation().getY());
        }

        return dto;
    }
}
