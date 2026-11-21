package com.bookmyrooms.main.repository;

import com.bookmyrooms.main.entities.rooms.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {
    Optional<Amenities> findByName(String name);
}
