package com.bookmyrooms.main.entities;

import com.bookmyrooms.main.enums.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int noOfBeds;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private double price;
    private boolean isAvailable;

    // for many to one set fetchtype lazy to avoid n+1 query problem
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "property_id", nullable = false)
    private Property property;
}
