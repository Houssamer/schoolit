package com.schoolit.schoolit.controllers.utilisateur;

import com.schoolit.schoolit.Exception.UtilisateurException;
import com.schoolit.schoolit.models.Admin;
import com.schoolit.schoolit.models.Apprenant;
import com.schoolit.schoolit.models.Utilisateur;
import com.schoolit.schoolit.models.requests.RegistrationRequest;
import com.schoolit.schoolit.services.registration.RegistrationService;
import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Objects;

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
    public ResponseEntity<?> getApprenantParId(@PathVariable Long id) {
        Utilisateur user = (Utilisateur) SecurityContextHolder
                                            .getContext()
                                            .getAuthentication()
                                            .getPrincipal();
        if (!Objects.equals(user.getId(), id)  || !(user instanceof Admin)) {
            return ResponseEntity.status(403).body("Not authorized");
        } else {
            return ResponseEntity.ok().body((Apprenant) utilisateurService.getUtilisateur(id));
        }
    }

    @GetMapping("/formations/{id}")
    public ResponseEntity<?> getFormationsSuivies(@PathVariable Long id) {
        Utilisateur user = (Utilisateur) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (!Objects.equals(user.getId(), id)  || !(user instanceof Admin)) {
            return ResponseEntity.status(403).body("Not authorized");
        } else {
            return ResponseEntity.ok().body(utilisateurService.getFormationsSuivies(id));
        }
    }

    @PostMapping("/find")
    public ResponseEntity<Apprenant> getApprenantParEmail(String email) {
        return ResponseEntity.ok().body((Apprenant) utilisateurService.getUtilisateurByEmail(email));
    }

    @PostMapping("/add")
    public ResponseEntity<?> ajouterApprenant(@RequestBody RegistrationRequest request) {
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/api/apprenant/add").toUriString());
        try {
            registrationService.registerApprenant(request);
            return ResponseEntity.created(uri).body("Apprenant bien ajoute");
        } catch (UtilisateurException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> modifierApprenant(@RequestBody Apprenant apprenant) {
        Utilisateur user = (Utilisateur) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (!Objects.equals(user.getId(), apprenant.getId()) || !(user instanceof Admin)) {
            return ResponseEntity.status(403).body("Unauthorized");
        } else {
            utilisateurService.modifierUtilisateur(apprenant);
            return ResponseEntity.ok("apprenant modifie");
        }
    }
}
