package com.schoolit.schoolit.services.formation;

import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.Formation;

import java.util.Collection;

public interface IFormationService {
    Collection<Formation> getFormations();
    Formation getFormation(Long id);
    Collection<Formation> getFormationsParFormateur(Formateur formateur);
    Collection<Formation> getFormationsParSpecialite(String specialite);
    Formation getFormationParNom(String nom);
    Formation ajouterFormation(Formation formation);
    void modifierFormation(Formation formation);
    void deleteFormation(Long id);
}
