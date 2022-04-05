package com.schoolit.schoolit.repos;

import com.schoolit.schoolit.models.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursRepo extends JpaRepository<Cours, Long> {
}
