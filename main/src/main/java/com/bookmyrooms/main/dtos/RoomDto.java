package com.bookmyrooms.main.dtos;

import com.bookmyrooms.main.enums.RoomType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {
    private Long roomNumber;
    private double areaInSqFt;
    private int noOfBeds;
    private RoomType roomType;
    private double price;
    private boolean isAvailable;
}
