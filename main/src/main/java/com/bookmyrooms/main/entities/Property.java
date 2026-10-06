package com.bookmyrooms.main.entities;

import com.bookmyrooms.main.enums.PropertyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;

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

    private String propertyName;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Column(columnDefinition = "geography(Point, 4326)")
    private Point location;

    @OneToMany(mappedBy = "property",
        cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Room> rooms = new ArrayList<>();


    public Property(String name) {
        this.propertyName = name;
    }

    // helper class to add and remove rooms
    public void addRoom(Room room){
        rooms.add(room);
        room.setProperty(this);
    }

    public void removeRoom(Room room){
        rooms.remove(room);
        room.setProperty(null);
    }
}
