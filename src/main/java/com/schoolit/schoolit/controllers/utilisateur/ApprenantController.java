package com.schoolit.schoolit.controllers.utilisateur;

import com.schoolit.schoolit.models.Apprenant;
import com.schoolit.schoolit.models.requests.RegistrationRequest;
import com.schoolit.schoolit.services.registration.RegistrationService;
import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Collection<Apprenant>> getAllApprenant() {
        return ResponseEntity.ok().body(utilisateurService.getApprenants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apprenant> getApprenantParId(@PathVariable Long id) {
        return ResponseEntity.ok().body((Apprenant) utilisateurService.getUtilisateur(id));
    }

    @PostMapping("/find")
    public ResponseEntity<Apprenant> getApprenantParEmail(String email) {
        return ResponseEntity.ok().body((Apprenant) utilisateurService.getUtilisateurByEmail(email));
    }

    @PostMapping("/add")
    public ResponseEntity<Apprenant> ajouterApprenant(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok().body(registrationService.registerApprenant(request));
    }

    @PutMapping("/update")
    public ResponseEntity<?> modifierApprenant(@RequestBody Apprenant apprenant) {
        utilisateurService.modifierUtilisateur(apprenant);
        return ResponseEntity.ok("done");
    }
}
