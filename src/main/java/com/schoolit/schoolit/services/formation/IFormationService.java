package com.schoolit.schoolit.services.formation;

import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.Formation;

import java.util.Collection;

public interface IFormationService {
    Collection<Formation> getFormations();
    Formation getFormation(Long id);
    Collection<Formation> getFormationParFormateur(Formateur formateur);
    Collection<Formation> getFormationParSpecialite(String specialite);
    Formation getFormationParNom(String nom);
    Formation ajouterFormation(Formation formation);
    String modifierFormation(Formation formation);
    void deleteFormation(Long id);
}
