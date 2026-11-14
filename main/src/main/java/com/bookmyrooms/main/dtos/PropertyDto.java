package com.bookmyrooms.main.dtos;

import com.bookmyrooms.main.entities.Address;
import com.bookmyrooms.main.entities.PrivacyOption;
import com.bookmyrooms.main.entities.Property;
import com.bookmyrooms.main.entities.misc.Capacity;
import com.bookmyrooms.main.entities.misc.Location;
import com.bookmyrooms.main.entities.misc.Pricing;
import com.bookmyrooms.main.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropertyDto {
    private Long propertyId;
    private String title;
    private String description;
    private Address address;
    private PrivacyOption privacyOption;
    private String imageName;
    private Location location;
}
