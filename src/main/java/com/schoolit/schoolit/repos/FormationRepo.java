package com.schoolit.schoolit.repos;

import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FormationRepo extends JpaRepository<Formation, Long> {
    Collection<Formation> findFormationsByFormateur(Formateur formateur);
    Collection<Formation> findFormationsBySpecialite(String specialite);
    Formation findFormationByNom(String nom);
}
