package com.bookmyrooms.main.entities.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "privacy_options")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PrivacyOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long privacyOptionId;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @OneToMany
    @JsonIgnore
    private List<Property> property;
}
