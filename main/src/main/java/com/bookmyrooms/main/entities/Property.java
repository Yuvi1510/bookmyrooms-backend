package com.bookmyrooms.main.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "properties")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    private String name;

    @Column(columnDefinition = "geography(Point, 4326)")
    private Point location;

    public Property(String name) {
        this.name = name;
    }
}
