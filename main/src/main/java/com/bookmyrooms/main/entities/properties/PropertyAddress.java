package com.bookmyrooms.main.entities.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "property_addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropertyAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String country;
    private String street;
    private String city;
    private String province;
    private String postalCode;

    @OneToOne
    @JsonIgnore
    private Property property;
}
