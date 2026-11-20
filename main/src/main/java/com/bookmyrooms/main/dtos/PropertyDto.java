package com.bookmyrooms.main.dtos;

import com.bookmyrooms.main.entities.Address;
import com.bookmyrooms.main.entities.PrivacyOption;
import com.bookmyrooms.main.entities.misc.Location;
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
    private boolean isVerified;


    public PropertyDto(Long propertyId, String title, String description, Address address, PrivacyOption privacyOption, boolean isVerified){
        this.propertyId = propertyId;
        this.title = title;
        this.description = description;
        this.address = address;
        this.privacyOption = privacyOption;
        this.isVerified = isVerified;
    }
}
