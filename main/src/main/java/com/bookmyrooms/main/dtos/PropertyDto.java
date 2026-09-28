package com.bookmyrooms.main.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropertyDto {
    private Long propertyId;
    private String name;
    private double lon;
    private double lat;
}
