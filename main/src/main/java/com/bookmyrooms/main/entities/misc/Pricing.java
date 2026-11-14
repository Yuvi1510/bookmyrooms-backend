package com.bookmyrooms.main.entities.misc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pricing {
    private double basePrice;
    private int weekendAdjustment;
    private double cleaningFee;
}
