package com.bookmyrooms.main.controllers;

import com.bookmyrooms.main.dtos.NearbyPropertyDto;
import com.bookmyrooms.main.dtos.PropertyDto;
import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.request.PropertyListingRequest;
import com.bookmyrooms.main.services.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Allow only your React app
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
    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public PropertyDto addProperty(
            @RequestPart("data")PropertyListingRequest propertyListingRequest,
            @RequestPart("images") MultipartFile file){
       return this.propertyService.saveProperty(propertyListingRequest, file);

    }

    // get all properties
    @GetMapping
    public Page<PropertyDto> getProperties(@PageableDefault(page = 0, size = 6, sort = "title") Pageable pageable){
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

}
