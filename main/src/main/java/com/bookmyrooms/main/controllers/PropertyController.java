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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // add properties
    @PostMapping("/add")
    public PropertyDto addProperty(@RequestBody PropertyDto propertyDto){
       return this.propertyService.saveProperty(propertyDto);

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

    @GetMapping("/{propertyId}")
    public PropertyDto getPropertyById(@PathVariable Long propertyId){
        return this.propertyService.getPropertyById(propertyId);
    }


    @DeleteMapping("/{propertyId}")
    public ResponseEntity<?> deletePropertyById(@PathVariable Long propertyId){
        boolean isDeleted = this.propertyService.deleteProperty(propertyId);

        if(isDeleted){
            return new ResponseEntity<>("Delete success", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
    }


    /// for rooms of properties
    @PostMapping("/{propertyId}/addroom")
    public ResponseEntity<?> addRoom(@PathVariable Long propertyId,
                                  @RequestBody RoomDto roomDto){
       RoomDto savedRoom = this.propertyService.addRoom(propertyId, roomDto);
       return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }
}
