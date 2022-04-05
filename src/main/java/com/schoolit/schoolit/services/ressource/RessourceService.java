package com.schoolit.schoolit.services.ressource;

import com.schoolit.schoolit.repos.RessourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RessourceService implements IRessourceService {
    private final RessourceRepo ressourceRepo;

    @Autowired
    public RessourceService(RessourceRepo ressourceRepo) {
        this.ressourceRepo = ressourceRepo;
    }

}
