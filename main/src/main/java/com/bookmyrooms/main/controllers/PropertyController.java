package com.bookmyrooms.main.controllers;

import com.bookmyrooms.main.dtos.NearbyPropertyDto;
import com.bookmyrooms.main.dtos.PropertyDto;
import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.entities.Property;
import com.bookmyrooms.main.services.PropertyService;
import com.bookmyrooms.main.services.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final PropertyService propertyService;
    private final ModelMapper modelMapper;
    private final RoomService roomService;

    @Autowired
    public PropertyController(PropertyService propertyService, ModelMapper modelMapper, RoomService roomService) {
        this.propertyService = propertyService;
        this.modelMapper = modelMapper;
        this.roomService = roomService;
    }

    // add properties
    @PostMapping("/add")
    public PropertyDto addProperty(@RequestBody PropertyDto propertyDto){
        Property property = new Property(propertyDto.getName());
       return this.propertyService.saveProperty(property, propertyDto.getLon(), propertyDto.getLat());

    }

    // get all properties
    @GetMapping
    public Page<PropertyDto> getProperties(@PageableDefault(page = 0, size = 6, sort = "name") Pageable pageable){
        return this.propertyService.getProperties(pageable);
    }

    // get properties within 2km radius and sorted by distance in asc
    @GetMapping("/nearby")
    public Page<NearbyPropertyDto> getPropertiesInRadius(@RequestParam double lon,
                                                         @RequestParam double lat,
                                                         @PageableDefault(page = 0, size = 6, sort = "distance") Pageable pageable){
        return this.propertyService.getPropertiesInRadius(lon, lat, pageable);
    }


    /// for rooms of properties
    @PostMapping("/{propertyId}/addroom")
    public boolean addRoom(@PathVariable Long propertyId,
                           @RequestBody RoomDto roomDto){
       return this.roomService.addRoom(propertyId, roomDto);
    }
}
