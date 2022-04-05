package com.schoolit.schoolit.repos;

import com.schoolit.schoolit.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepo extends JpaRepository<Formation, Long> {
}
