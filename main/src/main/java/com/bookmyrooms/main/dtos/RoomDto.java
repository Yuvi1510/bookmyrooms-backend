package com.bookmyrooms.main.dtos;

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
    private double price;
    private boolean isAvailable;
    private String propertyName;
}
