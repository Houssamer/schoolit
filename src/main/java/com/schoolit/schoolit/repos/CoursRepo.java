package com.schoolit.schoolit.repos;

import com.schoolit.schoolit.models.Cours;
import com.schoolit.schoolit.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CoursRepo extends JpaRepository<Cours, Long> {
    Cours findCoursByTitre(String titre);
    Collection<Cours> findCoursByFormation(Formation formation);
}
