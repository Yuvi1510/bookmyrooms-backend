package com.bookmyrooms.main.controllers;

import com.bookmyrooms.main.dtos.NearbyPropertyDto;
import com.bookmyrooms.main.dtos.PropertyDto;
import com.bookmyrooms.main.entities.Property;
import com.bookmyrooms.main.services.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final PropertyService propertyService;
    private final ModelMapper modelMapper;

    @Autowired
    public PropertyController(PropertyService propertyService, ModelMapper modelMapper) {
        this.propertyService = propertyService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public PropertyDto addProperty(@RequestBody PropertyDto propertyDto){
        Property property = new Property(propertyDto.getName());
       return this.propertyService.saveProperty(property, propertyDto.getLon(), propertyDto.getLat());

    }

    @GetMapping("/nearby")
    public List<NearbyPropertyDto> getPropertiesInRadius(@RequestParam double lon, @RequestParam double lat){
        return this.propertyService.getPropertiesInRadius(lon, lat);
    }
}
