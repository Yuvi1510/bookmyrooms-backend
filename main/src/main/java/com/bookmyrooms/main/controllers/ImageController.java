package com.bookmyrooms.main.controllers;

import com.bookmyrooms.main.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bookmyrooms.main.utilities.FileHandler;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Allow only your React app
@RequestMapping("api/images")
public class ImageController {
    @Value("${project.propertyImages}")
    private String propertyImagesPath;

    @Value("${project.roomImages}")
    private String roomImagesPath;


    @GetMapping("/properties")
    public ResponseEntity<Resource> getpropertyImage(@RequestParam String img){
        return FileHandler.serveImage(propertyImagesPath, img);
    }

    @GetMapping("/rooms")
    public ResponseEntity<Resource> getRoomImage(@RequestParam String img){
        return FileHandler.serveImage(roomImagesPath, img);
    }
}
