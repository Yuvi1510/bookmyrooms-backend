package com.bookmyrooms.main.repository;

import com.bookmyrooms.main.entities.PrivacyOption;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivacyOptionRepository extends JpaRepository<PrivacyOption, Long> {
    PrivacyOption findByType(String type);
}
