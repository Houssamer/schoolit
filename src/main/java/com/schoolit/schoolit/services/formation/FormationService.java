package com.schoolit.schoolit.services.formation;

import com.schoolit.schoolit.repos.FormationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FormationService implements IFormationService {
    private final FormationRepo formationRepo;

    @Autowired
    public FormationService(FormationRepo formationRepo) {
        this.formationRepo = formationRepo;
    }
}
