package com.bookmyrooms.main.entities.misc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Capacity {
    private int guests;
    private int bedrooms;
    private int beds;
    private int bathrooms;
}
