package com.bookmyrooms.main.repository;

import com.bookmyrooms.main.entities.properties.PrivacyOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivacyOptionRepository extends JpaRepository<PrivacyOption, Long> {
    Optional<PrivacyOption> findByType(String type);
}
