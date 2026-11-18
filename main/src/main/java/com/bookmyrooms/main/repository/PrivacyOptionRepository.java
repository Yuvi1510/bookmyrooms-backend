package com.bookmyrooms.main.repository;

import com.bookmyrooms.main.entities.PrivacyOption;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivacyOptionRepository extends JpaRepository<PrivacyOption, Long> {
    Optional<PrivacyOption> findByType(String type);
}
