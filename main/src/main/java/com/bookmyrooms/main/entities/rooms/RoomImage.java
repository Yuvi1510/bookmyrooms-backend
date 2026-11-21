package com.bookmyrooms.main.entities.rooms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "room_images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomImageId;

    private String name;

    @JsonIgnore
    @ManyToOne
    private Room room;

    public RoomImage(String name){
        this.name = name;
    }
}
