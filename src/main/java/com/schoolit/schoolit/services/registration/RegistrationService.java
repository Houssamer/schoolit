package com.schoolit.schoolit.services.registration;

import com.schoolit.schoolit.models.Apprenant;
import com.schoolit.schoolit.models.ConfirmationToken;
import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.requests.RegistrationRequest;
import com.schoolit.schoolit.services.confirmationtoken.ConfirmationTokenService;
import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationService implements IRegistrationService {
    private final UtilisateurService utilisateurService;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public RegistrationService(UtilisateurService utilisateurService,
                               ConfirmationTokenService confirmationTokenService) {
        this.utilisateurService = utilisateurService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public void registerFormateur(RegistrationRequest request) {
        utilisateurService.ajouterFormateur(new Formateur(
               request.getNom(),
               request.getPrenom(),
               request.getEmail(),
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
                request.getDateNaissance(),
                request.getPassword()
        ));
    }

    @Override
    public String confirmationToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));
        if (confirmationToken.getConfirmedAt()!=null) {
            throw new IllegalStateException("Email already confirmed");
        }
        LocalDateTime expiresAt = confirmationToken.getExpiresAt();
        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        utilisateurService.unlockUtilisateur(
                confirmationToken.getUtilisateur().getEmail()
        );
        return "confirmed";
    }
}
