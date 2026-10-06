package com.bookmyrooms.main.controllers;

import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.services.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public Page<RoomDto> getAllRooms(
            @PageableDefault(page = 0,
                    size = 6,
                    direction = Sort.Direction.ASC)
            Pageable pageable){
        return this.roomService.getAllRooms(pageable);
    }

}
