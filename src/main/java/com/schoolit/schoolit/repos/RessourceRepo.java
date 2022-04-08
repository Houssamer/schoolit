package com.schoolit.schoolit.repos;

import com.schoolit.schoolit.models.Cours;
import com.schoolit.schoolit.models.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RessourceRepo extends JpaRepository<Ressource, Long> {
    Collection<Ressource> findByCours(Cours cours);
    Ressource findByTitre(String titre);
}
