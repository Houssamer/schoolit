package com.schoolit.schoolit.services.cours;

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
        return null;
    }

    @Override
    public Cours getCoursParTitre(String titre) {
        return null;
    }

    @Override
    public Collection<Cours> getCoursParFormation(Formation formation) {
        return null;
    }

    @Override
    public Collection<Cours> getCours() {
        return null;
    }

    @Override
    public Cours ajouterCours(Cours cours) {
        return null;
    }

    @Override
    public String modifierCours(Cours cours) {
        return null;
    }

    @Override
    public void deleteCours(Long id) {

    }
}
