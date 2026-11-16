package com.bookmyrooms.main.request;

import com.bookmyrooms.main.entities.Address;
import com.bookmyrooms.main.entities.misc.Location;
import com.bookmyrooms.main.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropertyListingRequest {
    private Address address;
    private PropertyType propertyStructure;
    private String privacyType;
    private Location location;
    private String title;
    private String description;

    // capacity(floor plan), amenities and pricing will be included in the add rooms feature
    // only one image for property
    // multiple images for room

}
