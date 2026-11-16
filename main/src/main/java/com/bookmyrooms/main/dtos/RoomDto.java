package com.bookmyrooms.main.dtos;

import com.bookmyrooms.main.entities.Amenities;
import com.bookmyrooms.main.entities.misc.Capacity;
import com.bookmyrooms.main.entities.misc.Pricing;
import com.bookmyrooms.main.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RoomDto {
    private Long roomId;
    private Long roomNumber;
    private double areaInSqFt;
    private int beds;
    private int guests;
    private int bedrooms;
    private int bathrooms;
    private RoomType roomType;
    private double basePrice;
    private int weekendAdjustment;
    private double cleaningFee;
    private List<Amenities> amenities;
    private boolean isAvailable;
}
