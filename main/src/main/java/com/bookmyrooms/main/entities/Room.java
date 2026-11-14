package com.bookmyrooms.main.entities;

import com.bookmyrooms.main.enums.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long roomNumber;
    private double areaInSqFt;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private double basePrice;
    private int weekendAdjustment;
    private double cleaningFee;

    private int guests;
    private int bedrooms;
    private int beds;
    private int bathrooms;
    private boolean isAvailable;

    @ManyToMany
    private List<Amenities> amenities;

    // for many to one set fetchtype lazy to avoid n+1 query problem
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "property_id", nullable = false)
    private Property property;
}
