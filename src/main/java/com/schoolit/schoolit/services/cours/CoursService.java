package com.schoolit.schoolit.services.cours;

import com.schoolit.schoolit.Exception.CoursException;
import com.schoolit.schoolit.models.Cours;
import com.schoolit.schoolit.models.Formation;
import com.schoolit.schoolit.repos.CoursRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class CoursService implements ICoursService {
    private final CoursRepo coursRepo;

    @Autowired
    public CoursService(CoursRepo coursRepo) {
        this.coursRepo = coursRepo;
    }

    @Override
    public Cours GetCours(Long id) {
        return coursRepo.getById(id);
    }

    @Override
    public Cours getCoursParTitre(String titre) {
        return coursRepo.findCoursByTitre(titre);
    }

    @Override
    public Collection<Cours> getCoursParFormation(Formation formation) {
        return coursRepo.findCoursByFormation(formation);
    }

    @Override
    public Collection<Cours> getCours() {
        return coursRepo.findAll();
    }

    @Override
    public void ajouterCours(Cours cours) {
        boolean exist = coursRepo.existsById(cours.getId());
        if (exist) {
            throw new CoursException("Cours deja existe");
        } else {
            coursRepo.save(cours);
        }
    }

    @Override
    public String modifierCours(Cours cours) {
        boolean exist = coursRepo.existsById(cours.getId());
        if (exist) {
            coursRepo.save(cours);
        } else {
            throw new CoursException();
        }
        return null;
    }

    @Override
    public void deleteCours(Long id) {
        boolean exist = coursRepo.existsById(id);
        if (exist) {
            coursRepo.deleteById(id);
        } else {
            throw new CoursException();
        }
    }
}
