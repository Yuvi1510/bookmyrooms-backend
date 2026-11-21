package com.bookmyrooms.main.request;

import com.bookmyrooms.main.entities.misc.Capacity;
import com.bookmyrooms.main.entities.misc.Pricing;
import com.bookmyrooms.main.entities.rooms.Amenities;
import com.bookmyrooms.main.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddRoomRequest {
    private int roomNumber;
    private double areaInSqFt;
    private RoomType roomType;

    private Capacity capacity;
    private Pricing pricing;
    private List<String> amenities;
}
