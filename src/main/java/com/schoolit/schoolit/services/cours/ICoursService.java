package com.schoolit.schoolit.services.cours;

import com.schoolit.schoolit.models.Cours;
import com.schoolit.schoolit.models.Formation;

import java.util.Collection;

public interface ICoursService {
    Cours GetCours(Long id);
    Cours getCoursParTitre(String titre);
    Collection<Cours> getCoursParFormation(Formation formation);
    Collection<Cours> getCours();
    Cours ajouterCours(Cours cours);
    String modifierCours(Cours cours);
    void deleteCours(Long id);
}
