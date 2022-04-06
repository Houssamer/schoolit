package com.schoolit.schoolit.services.formation;

import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.Formation;
import com.schoolit.schoolit.repos.FormationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class FormationService implements IFormationService {
    private final FormationRepo formationRepo;

    @Autowired
    public FormationService(FormationRepo formationRepo) {
        this.formationRepo = formationRepo;
    }

    @Override
    public Collection<Formation> getFormations() {
        return null;
    }

    @Override
    public Formation getFormation(Long id) {
        return null;
    }

    @Override
    public Collection<Formation> getFormationParFormateur(Formateur formateur) {
        return null;
    }

    @Override
    public Collection<Formation> getFormationParSpecialite(String specialite) {
        return null;
    }

    @Override
    public Formation getFormationParNom(String nom) {
        return null;
    }

    @Override
    public Formation ajouterFormation(Formation formation) {
        return null;
    }

    @Override
    public String modifierFormation(Formation formation) {
        return null;
    }

    @Override
    public void deleteFormation(Long id) {

    }
}
