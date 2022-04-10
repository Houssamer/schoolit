package com.schoolit.schoolit.controllers.utilisateur;

import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.requests.RegistrationRequest;
import com.schoolit.schoolit.services.registration.RegistrationService;
import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/formateur")
public class FormateurController {
    private final UtilisateurService utilisateurService;
    private final RegistrationService registrationService;

    @Autowired
    public FormateurController(UtilisateurService utilisateurService,
                               RegistrationService registrationService) {
        this.utilisateurService = utilisateurService;
        this.registrationService = registrationService;
    }

    @GetMapping("/all")
    public Collection<Formateur> getAllFormateur() {
        return utilisateurService.getFormateurs();
    }

    @GetMapping("/{id}")
    public Formateur getFormateurParId(@PathVariable Long id) {
        return (Formateur) utilisateurService.getUtilisateur(id);
    }

    @GetMapping("/disbaled")
    public Collection<Formateur> getFormateurNonVerifie() {
        return utilisateurService.getFormateurNonVerifie();
    }

    @GetMapping("/enable/{id}")
    public String enableFormateur(@PathVariable Long id) {
        return utilisateurService.enableCompte(id);
    }

    @PostMapping("/find")
    public Formateur getFormateurParEmail(String email) {
        return (Formateur) utilisateurService.getUtilisateurByEmail(email);
    }

    @PostMapping("/add")
    public void ajouterFormateur(@RequestBody RegistrationRequest request) {
        registrationService.registerFormateur(request);
    }

    @PutMapping("/update")
    public void modifierFormateur(@RequestBody Formateur formateur) {
        utilisateurService.modifierUtilisateur(formateur);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFormateur(@PathVariable Long id) {
        utilisateurService.deleteFormateur(id);
    }
}
