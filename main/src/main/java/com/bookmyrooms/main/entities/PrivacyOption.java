package com.bookmyrooms.main.entities;

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

    private String type;

    private String description;

    @OneToMany
    private List<Property> property;
}
