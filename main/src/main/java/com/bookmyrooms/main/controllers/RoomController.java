package com.bookmyrooms.main.controllers;

import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.services.RoomService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

}
