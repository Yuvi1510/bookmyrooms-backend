package com.bookmyrooms.main.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "property_images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropertyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyImageId;

    private String imageName;

    @OneToOne
    private Property property;

    public PropertyImage(String name){
        this.imageName = name;
    }
}
