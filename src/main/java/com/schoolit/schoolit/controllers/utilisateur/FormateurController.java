package com.schoolit.schoolit.controllers.utilisateur;

import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/formateur")
public class FormateurController {
    private final UtilisateurService utilisateurService;

    @Autowired
    public FormateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
}
