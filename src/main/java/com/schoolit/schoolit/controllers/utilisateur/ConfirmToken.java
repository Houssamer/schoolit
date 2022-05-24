package com.schoolit.schoolit.controllers.utilisateur;

import com.schoolit.schoolit.services.registration.IRegistrationService;
import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class ConfirmToken {
    private final IRegistrationService registrationService;

    @Autowired
    public ConfirmToken(IRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestParam("token") String token) {
        String conf = registrationService.confirmationToken(token);
        return  ResponseEntity.accepted().body(conf);
    }
}
