package com.bookmyrooms.main.entities;

import com.bookmyrooms.main.enums.PropertyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Column(columnDefinition = "geography(Point, 4326)")
    private Point location;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    @JsonIgnore
    private Address address;

    @OneToMany(mappedBy = "property",
        cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Room> rooms = new ArrayList<>();

    @ManyToOne
    private PrivacyOption privacyOption;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    @JsonIgnore
    private PropertyImage propertyImage;

    // helper class to add and remove rooms
    public void addRoom(Room room){
        rooms.add(room);
        room.setProperty(this);
    }

    public void removeRoom(Room room){
        rooms.remove(room);
        room.setProperty(null);
    }

    public void setPropertyImage(PropertyImage propertyImage){
        this.propertyImage = propertyImage;
        propertyImage.setProperty(this);
    }

    public void setAddress(Address address){
        this.address = address;
        address.setProperty(this);
    }

    public void setPrivacyOption(PrivacyOption privacyOption){
        this.privacyOption = privacyOption;
        privacyOption.getProperty().add(this);
    }
}
