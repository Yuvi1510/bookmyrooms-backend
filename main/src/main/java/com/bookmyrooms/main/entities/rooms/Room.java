package com.bookmyrooms.main.entities.rooms;

import com.bookmyrooms.main.entities.properties.Property;
import com.bookmyrooms.main.entities.properties.PropertyImage;
import com.bookmyrooms.main.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private int roomNumber;
    private String description;
    private double areaInSqFt;


    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomImage> images = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private double basePrice;
    private int weekendAdjustment;
    private double cleaningFee;

    private int guests;
    private int bedrooms;
    private int beds;
    private int bathrooms;
    private boolean isAvailable = true;

    @ManyToMany

    private List<Amenities> amenities = new ArrayList<>();

    // for many to one set fetchtype lazy to avoid n+1 query problem

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    // add ammenities
    public void setAmenities(List<Amenities> amenities){
        for(Amenities amenity: amenities){
            this.amenities.add(amenity);
            amenity.getRooms().add(this);
        }
    }

    public void addImage(RoomImage image){
        this.images.add(image);
        image.setRoom(this);
    }

    public Room(int roomNumber, double areaInSqFt, RoomType roomType, double basePrice, int weekendAdjustment, double cleaningFee, int guests, int bedrooms, int beds, int bathrooms, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.areaInSqFt = areaInSqFt;
        this.roomType = roomType;
        this.basePrice = basePrice;
        this.weekendAdjustment = weekendAdjustment;
        this.cleaningFee = cleaningFee;
        this.guests = guests;
        this.bedrooms = bedrooms;
        this.beds = beds;
        this.bathrooms = bathrooms;
        this.isAvailable = isAvailable;

    }
}
