package com.bookmyrooms.main.repository;

import com.bookmyrooms.main.dtos.NearbyPropertyDto;
import com.bookmyrooms.main.dtos.PropertyDto;
import com.bookmyrooms.main.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query(value = """
        SELECT property_id, name , 
               ST_X(location::geometry) AS lon,
               ST_Y(location::geometry) AS lat,
               ST_Distance(
                  location::geography,
                  ST_SetSRID(
                  ST_MakePoint(:lon, :lat),
                  4326
                  )::geography
        ) AS distance
        FROM properties 
        WHERE ST_DWithin(
        location::geography,
        ST_SetSRID(ST_MakePoint(:lon, :lat), 4326)::geography, 2000
        ) ORDER BY distance
""", nativeQuery = true)
    List<NearbyPropertyDto> findPropertiesWithinRadius(
            @Param("lon") double lon,
            @Param("lat") double lat
    );
}
