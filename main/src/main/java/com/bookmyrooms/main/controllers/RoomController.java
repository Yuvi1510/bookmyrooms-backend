package com.bookmyrooms.main.controllers;

import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.entities.rooms.Room;
import com.bookmyrooms.main.request.AddRoomRequest;
import com.bookmyrooms.main.services.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Allow only your React app
@RequestMapping("/api")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(value = "rooms/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addRoom(
            @RequestPart("data") AddRoomRequest addRoomRequest,
            @RequestPart("images")List<MultipartFile> files,
            @RequestParam("propertyId") Long propertyId){
       RoomDto roomDto = this.roomService.addRoom(
               addRoomRequest,
               files,
               propertyId
       );

       return ResponseEntity.ok().body(roomDto);
    }

    @GetMapping("properties/{propertyId}/rooms")
    public ResponseEntity<?> getRoomsByPropertyId(@PathVariable Long propertyId,
                                                  @PageableDefault(size = 10, page = 0, sort = "roomId") Pageable pageable){
        Page<RoomDto> rooms = this.roomService.getRoomsByPropertyId(propertyId, pageable);

        return ResponseEntity.ok().body(rooms);
    }

    @GetMapping("/rooms")
    public ResponseEntity<?> getAllRooms(
            @PageableDefault(page = 0,
                    size = 6,
                    sort = "roomNumber",
                    direction = Sort.Direction.ASC)
            Pageable pageable){
        Page<RoomDto> rooms =  this.roomService.getAllRooms(pageable);

        return ResponseEntity.ok().body(rooms);
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<?> getRoomById(@PathVariable Long roomId){
        RoomDto roomDto = this.roomService.getRoomById(roomId);
        return ResponseEntity.ok().body(roomDto);
    }
}
