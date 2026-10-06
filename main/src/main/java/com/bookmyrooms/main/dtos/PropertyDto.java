package com.bookmyrooms.main.dtos;

import com.bookmyrooms.main.entities.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropertyDto {
    private String propertyName;
    private double lon;
    private double lat;
    private int noOfRooms;
}
