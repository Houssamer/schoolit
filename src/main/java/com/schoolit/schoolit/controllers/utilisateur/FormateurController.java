package com.schoolit.schoolit.controllers.utilisateur;

import com.schoolit.schoolit.Exception.UtilisateurException;
import com.schoolit.schoolit.models.Admin;
import com.schoolit.schoolit.models.Formateur;
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
    public ResponseEntity<Collection<Formateur>> getAllFormateur() {
        return ResponseEntity.ok().body(utilisateurService.getFormateurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFormateurParId(@PathVariable Long id) {
        Utilisateur user = (Utilisateur) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (!Objects.equals(user.getId(), id)  || !(user instanceof Admin)) {
            return ResponseEntity.status(403).body("Unauthorized");
        } else {
            return ResponseEntity.ok().body((Formateur) utilisateurService.getUtilisateur(id));
        }
    }

    @GetMapping("/formations/{id}")
    public ResponseEntity<?> getFormationsCrees(@PathVariable Long id) {
        Utilisateur user = (Utilisateur) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (!Objects.equals(user.getId(), id)  || !(user instanceof Admin)) {
            return ResponseEntity.status(403).body("Not authorized");
        } else {
            return ResponseEntity.ok().body(utilisateurService.getFormationsCrees(id));
        }
    }

    @GetMapping("/disbaled")
    public ResponseEntity<Collection<Formateur>> getFormateurNonVerifie() {
        return ResponseEntity.ok().body(utilisateurService.getFormateurNonVerifie());
    }

    @GetMapping("/enable/{id}")
    public ResponseEntity<?> enableFormateur(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.enableCompte(id));
    }

    @PostMapping("/find")
    public ResponseEntity<Formateur> getFormateurParEmail(String email) {
        return ResponseEntity.ok().body((Formateur) utilisateurService.getUtilisateurByEmail(email));
    }

    @PostMapping("/add")
    public ResponseEntity<?> ajouterFormateur(@RequestBody RegistrationRequest request) {
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/api/formateur/add").toUriString());
        try {
            registrationService.registerFormateur(request);
            return ResponseEntity.created(uri).body("Formateur bien ajoute");
        } catch (UtilisateurException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> modifierFormateur(@RequestBody Formateur formateur) {
        Utilisateur user = (Utilisateur) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (!Objects.equals(user.getId(), formateur.getId())  || !(user instanceof Admin)) {
            return ResponseEntity.status(403).body("Unauthorized");
        } else {
            utilisateurService.modifierUtilisateur(formateur);
            return ResponseEntity.ok("formateur modifier");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFormateur(@PathVariable Long id) {
        Utilisateur user = (Utilisateur) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (!Objects.equals(user.getId(), id)  || !(user instanceof Admin)) {
            return ResponseEntity.status(403).body("Unauthorized");
        } else {
            utilisateurService.deleteFormateur(id);
            return ResponseEntity.ok("formateur supprime");
        }
    }
}
