package com.schoolit.schoolit.services.formation;

import com.schoolit.schoolit.Exception.FormationException;
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
        return formationRepo.findAll();
    }

    @Override
    public Formation getFormation(Long id) {
        return formationRepo.getById(id);
    }

    @Override
    public Collection<Formation> getFormationsParFormateur(Formateur formateur) {
        return formationRepo.findFormationsByFormateur(formateur);
    }

    @Override
    public Collection<Formation> getFormationsParSpecialite(String specialite) {
        return formationRepo.findFormationsBySpecialite(specialite);
    }

    @Override
    public Formation getFormationParNom(String nom) {
        return formationRepo.findFormationByNom(nom);
    }

    @Override
    public Formation ajouterFormation(Formation formation) {
        boolean exist = formationRepo.existsById(formation.getId());
        if (exist) {
            throw new FormationException("Formation deja existe");
        } else {
            return formationRepo.save(formation);
        }
    }

    @Override
    public void modifierFormation(Formation formation) {
        boolean exist = formationRepo.existsById(formation.getId());
        if (exist) {
            formationRepo.save(formation);
        } else {
            throw new FormationException();
        }
    }

    @Override
    public void deleteFormation(Long id) {
        boolean exist = formationRepo.existsById(id);
        if (exist) {
            formationRepo.deleteById(id);
        } else {
            throw new FormationException();
        }
    }
}
