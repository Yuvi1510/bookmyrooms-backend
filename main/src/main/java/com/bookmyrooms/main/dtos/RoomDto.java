package com.bookmyrooms.main.dtos;

import com.bookmyrooms.main.entities.misc.Capacity;
import com.bookmyrooms.main.entities.misc.Pricing;
import com.bookmyrooms.main.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomDto {
    private Long roomId;
    private Long roomNumber;
    private double areaInSqFt;
    private int noOfBeds;
    private RoomType roomType;
    private Pricing pricing;
    private Capacity capacity;
    private boolean isAvailable;
}
