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
    private String name;
    private double lon;
    private double lat;

    // method to convert property to propertydto
//    public static PropertyDto from(Property property){
//        return new PropertyDto(property.getName(), property.getLocation().getX(), property.getLocation().getY());
//    }
}
