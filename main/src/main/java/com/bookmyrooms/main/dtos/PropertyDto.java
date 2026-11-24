package com.bookmyrooms.main.dtos;

import com.bookmyrooms.main.entities.properties.PropertyAddress;
import com.bookmyrooms.main.entities.properties.PrivacyOption;
import com.bookmyrooms.main.entities.properties.Location;
import com.bookmyrooms.main.enums.PropertyType;
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
    private String title;
    private String description;
    private PropertyAddress propertyAddress;
    private PropertyType propertyType;
    private PrivacyOption privacyOption;
    private String imageName;
    private Location location;
    private boolean isVerified;


    public PropertyDto(Long propertyId, String title, String description, PropertyAddress propertyAddress, PrivacyOption privacyOption, boolean isVerified){
        this.propertyId = propertyId;
        this.title = title;
        this.description = description;
        this.propertyAddress = propertyAddress;
        this.privacyOption = privacyOption;
        this.isVerified = isVerified;
    }
}
