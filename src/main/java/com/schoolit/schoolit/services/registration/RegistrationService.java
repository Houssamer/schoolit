package com.schoolit.schoolit.services.registration;

import com.schoolit.schoolit.models.Apprenant;
import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.requests.RegistrationRequest;
import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService implements IRegistrationService {
    private final UtilisateurService utilisateurService;

    @Autowired
    public RegistrationService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public void registerFormateur(RegistrationRequest request) {
       utilisateurService.ajouterFormateur(new Formateur(
               request.getNom(),
               request.getPrenom(),
               request.getEmail(),
               request.getUsername(),
               request.getDateNaissance(),
               request.getPassword()
       ));
    }

    @Override
    public void registerApprenant(RegistrationRequest request) {
        utilisateurService.ajouterApprenant(new Apprenant(
                request.getNom(),
                request.getPrenom(),
                request.getEmail(),
                request.getUsername(),
                request.getDateNaissance(),
                request.getPassword()
        ));
    }
}
