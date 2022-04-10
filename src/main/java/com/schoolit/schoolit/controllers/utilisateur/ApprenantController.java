package com.schoolit.schoolit.controllers.utilisateur;

import com.schoolit.schoolit.models.Apprenant;
import com.schoolit.schoolit.models.requests.RegistrationRequest;
import com.schoolit.schoolit.services.registration.RegistrationService;
import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/apprenant")
public class ApprenantController {
    private final UtilisateurService utilisateurService;
    private final RegistrationService registrationService;

    @Autowired
    public ApprenantController(UtilisateurService utilisateurService,
                               RegistrationService registrationService) {
        this.utilisateurService = utilisateurService;
        this.registrationService = registrationService;
    }

    @GetMapping("/all")
    public Collection<Apprenant> getAllApprenant() {
        return utilisateurService.getApprenants();
    }

    @GetMapping("/{id}")
    public Apprenant getApprenantParId(@PathVariable Long id) {
        return (Apprenant) utilisateurService.getUtilisateur(id);
    }

    @PostMapping("/find")
    public Apprenant getApprenantParEmail(String email) {
        return (Apprenant) utilisateurService.getUtilisateurByEmail(email);
    }

    @PostMapping("/add")
    public void ajouterApprenant(@RequestBody RegistrationRequest request) {
        registrationService.registerApprenant(request);
    }

    @PutMapping("/update")
    public void modifierApprenant(@RequestBody Apprenant apprenant) {
        utilisateurService.modifierUtilisateur(apprenant);
    }
}
