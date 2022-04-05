package com.schoolit.schoolit.controllers.utilisateur;

import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/apprenant")
public class ApprenantController {
    private final UtilisateurService utilisateurService;

    @Autowired
    public ApprenantController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
}
